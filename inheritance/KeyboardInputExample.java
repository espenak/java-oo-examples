import java.util.Scanner;

class KeyboardInputException extends Exception {
    KeyboardInputException(String msg) {
		super(msg);
	}
}

class NotANumberException extends KeyboardInputException {
    NotANumberException(String msg) {
		super(msg);
	}
}

class InvalidEmailException extends KeyboardInputException {
    InvalidEmailException(String msg) {
		super(msg);
	}
}

class InvalidNameException extends KeyboardInputException {
    InvalidNameException(String msg) {
		super(msg);
	}
}


class KeyboardInput {
    Scanner terminal = new Scanner(System.in);

    boolean hasNextLine() {
        return terminal.hasNextLine();
    }

	int getInt() throws NotANumberException {
		String line = terminal.nextLine();
		try {
			return Integer.parseInt(line);
		} catch(NumberFormatException e) {
			throw new NotANumberException("Not a number: " + line);
		}
	}

	String getEmail() throws InvalidEmailException {
		String email = terminal.nextLine();
        if (email.indexOf('@') == -1) {
            throw new InvalidEmailException("Invalid email:" + email);
        }
		return email;
	}

	String getName() throws InvalidNameException {
		String name = terminal.nextLine();
		// Is reasonable? http://www.kalzumeus.com/2010/06/17/falsehoods-programmers-believe-about-names/
        if (name.split(" ").length < 2) {
            throw new InvalidNameException("Names must contain at least two words.");
        }
		return name;
	}
}


class KeyboardInputExample {
	public static void main(String[] args) {
		KeyboardInput in = new KeyboardInput();

		try {
			System.out.print("Age: ");
			System.out.println(in.getInt());

			System.out.print("Email: ");
			System.out.println(in.getEmail());

			System.out.print("Name: ");
			System.out.println(in.getName());
		} catch(KeyboardInputException e) {
			System.out.println(e.getMessage());
		}
	}
}
