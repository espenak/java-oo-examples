/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class SuperHeroListGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new SuperHeroListWindow();
            }
        });
    }
}


/**
 * The main window containing a SuperHeroList and a SuperMenu,
 *
 * @see SuperHeroList
 * @see SuperMenu
 */
class SuperHeroListWindow extends JFrame {

    public SuperHeroListWindow() {
        super("Super hero list");

        SuperHeroList superList = new SuperHeroList();
        JMenuBar menubar = new JMenuBar();

        menubar.add(new SuperMenu(superList));
        setJMenuBar(menubar);

        add(superList);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}


/**
 * A simple superhero representation with methods to read information.
 */
class SuperHero {

    private String name;
    private String superpowers;
    private String weakness;

    public SuperHero(String name, String superpowers, String weakness) {
        this.name = name;
        this.superpowers = superpowers;
        this.weakness = weakness;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getSuperpowers() {
        return superpowers;
    }

    public String getWeakness() {
        return weakness;
    }
}


/**
 * A custom SWING component to display information about superheroes.
 */
class SuperHeroList extends JPanel implements ActionListener {

    private SuperHeroInfo info;
    private JComboBox heroes;

    private class SuperHeroInfo extends JPanel {

        JLabel name, superpowers, weakness;

        public SuperHeroInfo() {
            name = new JLabel();
            superpowers = new JLabel();
            weakness = new JLabel();

            setLayout(new GridLayout(3, 2));
            add(new JLabel("Name:"));
            add(name);
            add(new JLabel("Superpowers:"));
            add(superpowers);
            add(new JLabel("Weakness:"));
            add(weakness);
        }

        public void setHero(SuperHero h) {
            name.setText(h.getName());
            superpowers.setText(h.getSuperpowers());
            weakness.setText(h.getWeakness());
        }
    }

    public SuperHeroList() {
        Vector<SuperHero> data = new Vector<SuperHero>();
        data.add(new SuperHero("Superman", "Speed, strength, flight ++",
                "Kryptonite"));
        data.add(new SuperHero("Invisible woman", "Invisibility", "none?"));
        data.add(new SuperHero("Human Torch", "Fire", "water?"));

        heroes = new JComboBox(data);
        heroes.addActionListener(this);
        add(heroes);

        info = new SuperHeroInfo();
        add(info);

        // Select first item in list
        heroes.setSelectedIndex(0);
        info.setHero(data.get(0));
    }

    //@Override ... Får feil i eclipse jp

    public void actionPerformed(ActionEvent e) {
        JComboBox sl = (JComboBox) e.getSource();
        SuperHero selectedHero = (SuperHero) sl.getSelectedItem();
        System.out.printf("name: %s, sp: %s%n", selectedHero.getName(),
                selectedHero.getSuperpowers());
        info.setHero(selectedHero);
    }

    /**
     * Add a superhero to this list.
     *
     * @param s The superhero.
     */
    public void addSuperHero(SuperHero s) {
        heroes.addItem(s);
    }

    /**
     * Remove the selected superhero from the list.
     */
    public void removeSelectedSuperHero() {
        heroes.removeItemAt(heroes.getSelectedIndex());
    }
}


/**
 * A pre-filled JMenu with add and remove buttons and event-listeners which
 * manipulates a SuperHeroList.
 *
 * @see SuperHeroList
 */
class SuperMenu extends JMenu implements ActionListener {
    private JMenuItem addBtn, removeBtn;
    private SuperHeroList heroList;

    public SuperMenu(SuperHeroList list) {
        super("Manage");
        this.heroList = list;

        addBtn = new JMenuItem("Add");
        removeBtn = new JMenuItem("Remove");

        addBtn.addActionListener(this);
        removeBtn.addActionListener(this);

        add(addBtn);
        add(removeBtn);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeBtn) {
            heroList.removeSelectedSuperHero();
        } else if (e.getSource() == addBtn) {
            System.out.println("Unsupported operation. " +
                    "Using a dialog is a good solution.");
		}
	}
}
