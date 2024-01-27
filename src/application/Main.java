package application;

import entietes.Product;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> list = new ArrayList<>();

        System.out.print("Enter file path: ");
        String sourceFileStr = sc.nextLine();

        File sourceFile = new File(sourceFileStr);
        String sourceFolderStr = sourceFile.getParent();

        boolean success = new File(sourceFolderStr + "\\out").mkdir();

        String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){

            String ItemCsv = br.readLine();
            while(ItemCsv != null){

                String[] fields = ItemCsv.split(",");
                String product = fields[0];
                double price = Double.parseDouble(fields[1]);
                int quantity = Integer.parseInt(fields[2].trim());

                list.add(new Product(product, price, quantity));

                ItemCsv = br.readLine();
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
                for(Product item : list){
                    bw.write(item.getProduct()+","+String.format("%.2f", item.tolal()));
                    bw.newLine();
                }

                System.out.println(targetFileStr+" Created!");
            }
            catch (IOException e){
                System.out.println("Error writing file: "+e.getMessage());
            }
        }
        catch (IOException e){
            System.out.println("Error writing file: "+e.getMessage());
        }

        sc.close();
    }
}
