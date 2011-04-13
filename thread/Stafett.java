package threads;

import java.awt.GridLayout;

import javax.swing.*;
import java.awt.Color;
import java.util.Arrays;
import java.util.Iterator;

public class Stafett implements Iterable<RelayTeam> {
    
    private RelayTeam[] teams;

    public static void main(String[] args) {

        final Stafett s =  new Stafett(8, 4, 100);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StafettGUI(s);
            }
        });

        s.startAndWaitForTeams();

        s.findAndSetWinner();
    }

    /**
     * @param t    Number of teams
     * @param r    Number of runners per team
     * @param dist Distance for each runner.
     */
    Stafett(int t, int r, int dist) {

        teams = new RelayTeam[t];

        for (int i = 1; i <= t; i++) {
            teams[i - 1] = new RelayTeam("Team " + i, r, dist, new RunnerPanel("Team " + i, r * dist));
        }
    }

    void startAndWaitForTeams() {

        int t = teams.length;

        // start
        for (int i = 0; i < t; i++)
            teams[i].start(); // tjuvstart? Kan det fikses?

        // wait
        for (int i = 0; i < t; i++) {
            try {
                teams[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void findAndSetWinner() {

        RunnerPanel first = null;
        int min = Integer.MAX_VALUE;
        
        for (RelayTeam rt: this) {

            RunnerPanel currentPanel = rt.getRunnerPanel();
            
            if (currentPanel.getTime() < min) {
                first = currentPanel;
                min = currentPanel.getTime();
            }
        }
        first.setFirst();
    }

    public int getNumberOfTeams() {
        return teams.length;
    }

    public Iterator<RelayTeam> iterator() {
        return Arrays.asList(teams).iterator();
    }
}

class RelayTeam extends Thread {
    
    private Runner firstRunner;
    private RunnerPanel rp;

    private Object lock;

    /**
     * @param s    Team name
     * @param r    Number of runners
     * @param dist Distance for each runner
     * @param rp   Gui pannel for this team
     */
    public RelayTeam(String s, int r, int dist, RunnerPanel rp) {
        super(s);
        this.rp = rp;
        Runner runner = null;

        for (int i = 1; i <= r; i++) {
            runner = new Runner(s + " Runner " + i, this, dist, runner, r + 1 - i);
        }
        firstRunner = runner;

        lock = new Object();
    }

    public void updateProgress(int newPos) {
        rp.updateProgress(newPos);
    }

    public void setRunner(int nr) {
        rp.setRunner(nr);
    }

    public RunnerPanel getRunnerPanel() {
        return rp;
    }

    @Override
    public void run() {
        
        long t1 = System.currentTimeMillis();
        firstRunner.start();
        waitOnTeam();
        long t2 = System.currentTimeMillis();

        rp.setTime(t2 - t1);
    }

    public void waitOnTeam() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void finish() {
        synchronized (lock) {
            lock.notify();
        }
    }
}

class Runner extends Thread {

    private Runner nextRunner;
    private RelayTeam myRT;
    private int dist;
    private int nr;

    /**
     * @param rt
     * @param dist
     * @param next
     * @param nr
     */
    public Runner(String name, RelayTeam rt, int dist, Runner next, int nr) {
        super(name);
        this.dist = dist;
        this.nr = nr;
        myRT = rt;
        nextRunner = next;
    }

    @Override
    public void run() {
        myRT.setRunner(nr);
        for (int i = 0; i < dist; i++) {
            try {
                Thread.sleep((long) (Math.random() * 50));
                myRT.updateProgress((nr - 1) * dist + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (nextRunner != null) {
            nextRunner.start();
        } else {
            myRT.finish();
        }
    }
}

class RunnerPanel extends JPanel {
    
    private JTextField runner, time;
    private JLabel team;
    private JProgressBar progress;

    RunnerPanel(String teamName, int max) {
        add(team = new JLabel(teamName));
        add(progress = new JProgressBar(0, max));
        add(runner = new JTextField(1));
        add(time = new JTextField(5));

        runner.setEditable(false);
        time.setEditable(false);
    }

    public void setTime(long time) {
        this.time.setText("" + time);
        this.repaint();
    }

    public void setRunner(int nr) {
        runner.setText("" + nr);
        this.repaint();
    }

    public void updateProgress(int newPos) {
        progress.setValue(newPos);
        this.repaint();
    }

    public int getTime() {
        return Integer.parseInt(time.getText());
    }

    public void setFirst() {
		time.setBackground(Color.YELLOW);
        this.repaint();
	}
}

class StafettGUI extends JFrame {

    public StafettGUI(Stafett stafett) {

        int numberOfTeams = stafett.getNumberOfTeams();

        setLayout(new GridLayout(numberOfTeams, 1));

        for (RelayTeam p: stafett) {
            this.add(p.getRunnerPanel());
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
