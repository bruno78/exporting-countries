
/**
 * Write a description of class ExportData here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ExportData
{
    public String countryInfo(CSVParser parser, String countryOfInterest){
        String info = "NOT FOUND";
        for ( CSVRecord record : parser ) {
            String country = record.get("Country");
            if (country.contains(countryOfInterest)) {
                info = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return info;
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){

        for ( CSVRecord record : parser ) {

            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for ( CSVRecord record : parser ) {
             String exports = record.get("Exports");
             if(exports.contains(exportItem)) {
                 count ++;
                }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount){
        int inputSize = amount.length();
        for (CSVRecord record : parser) {
            int recordAmount = record.get("Value (dollars)").length();
            if (recordAmount > inputSize){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    public void tester(){
        System.out.println("Returning countryInfo: ");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        //System.out.println(countryInfo(parser, "Nauru"));
        System.out.println("");

        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Praelele"));
        System.out.println("");

        System.out.println("Returning listExportersTwoProducts: ");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println("");

        System.out.println("Returning numberOfExporters: ");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        //System.out.println(numberOfExporters(parser, "sugar"));
        System.out.println("");

        System.out.println("Returning bigExporters: ");
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        System.out.println("");

    }
}
