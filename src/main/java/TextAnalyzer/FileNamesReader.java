package TextAnalyzer;

import java.util.Scanner;

public class FileNamesReader {

    public String[] readFromConsole(){
        Scanner scanner = new Scanner(System.in);
        int filesAmount = askFilesAmount(scanner);
        String[] paths = askFilePaths(scanner, filesAmount);
        return paths;
    }

    protected int askFilesAmount(Scanner scanner){
        int amount = 0;
        String am;
        while (amount <= 0) {
            System.out.println("Specify amount of files to be read: ");
            am = scanner.nextLine().trim();
            try {
                amount = Integer.parseInt(am);
            }
            catch (NumberFormatException e){
                System.out.println("Please, specify integer amount of files");
            }
            if (amount <= 0) {
                System.out.println("Amount of files should be greater than 0");
            }
        }
        return amount;
    }

    protected String[] askFilePaths(Scanner scanner, int amount){
        System.out.println("Please, type paths to files (each path on separate line)");

        String[] paths = new String[amount];
        for(int i = 0; i < amount; i++){
            boolean validPath = false;
            while (!validPath) {
                paths[i] = scanner.nextLine();
                if(paths[i].length() == 0){
                    System.out.println("Path to file can't be empty");
                }
                else {
                    validPath = true;
                    for (int j = i - 1; j >= 0; j--){
                        if(paths[j].compareTo(paths[i]) == 0){
                            System.out.println("File with this path has been specified already. Try another one");
                            validPath = false;
                            break;
                        }
                    }
                }
            }
        }
        return paths;
    }
}
