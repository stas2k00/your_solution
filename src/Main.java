import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            FileOutputStream outputStream = new FileOutputStream("src\\output.txt");
            Update best_bid = new Update();
            best_bid.setAskBid("bid");
            Update best_ask = new Update();
            best_ask.setAskBid("ask");
            ArrayList<Update> updates = new ArrayList<>();
            Scanner scanner = new Scanner(Paths.get("src\\input.txt").toAbsolutePath());
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] words = line.split(",");
                switch (words[0]) {
                    case "u":{
                        updates.add(new Update(Integer.parseInt(words[1]),Integer.parseInt(words[2]),words[3]));
                        if (updates.get(updates.size()-1).getAskBid().contentEquals("bid")  && updates.get(updates.size()-1).getPrice()>best_bid.getPrice()) {
                            best_bid = updates.get(updates.size()-1);
                        }
                        if (updates.get(updates.size()-1).getAskBid().contentEquals("ask")  && (updates.get(updates.size()-1).getPrice()<best_ask.getPrice() || best_ask.getPrice() < 1)) {
                            best_ask = updates.get(updates.size()-1);
                        }
                        break;
                    }
                    case "q":{
                        if (words[1].contentEquals("best_bid")) {
                            byte[] strToBytes = best_bid.toString().getBytes();
                            outputStream.write(strToBytes);
                        }
                        if (words[1].contentEquals("best_ask")) {
                            byte[] strToBytes = best_ask.toString().getBytes();
                            outputStream.write(strToBytes);
                        }
                        if (words[1].contentEquals("size")) {
                            ArrayList<Update> updates_copy = new ArrayList<>();
                            updates_copy.addAll(updates);
                            updates_copy.sort((update, t1) -> t1.getPrice()-update.getPrice());
                            updates_copy.removeIf(update -> update.getSize()==0);
                            updates_copy.forEach(update -> {
                                if (Integer.parseInt(words[2]) == update.getPrice()){
                                    byte[] strToBytes = update.toStringSize().getBytes();
                                    try {
                                        outputStream.write(strToBytes);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        }
                    }
                    case "o":{
                        if (words[1].contentEquals("sell")) {
                            int sizeSell = Integer.parseInt(words[2]);
                            if (best_bid.getSize() < sizeSell) {
                                sizeSell = sizeSell - best_bid.getSize();
                                best_bid.setSize(0);
                                updates.sort((update1, update2) -> update2.getPrice()- update1.getPrice());
                            } else {
                                best_bid.setSize(best_bid.getSize()-sizeSell);
                            }

                        }
                        if (words[1].contentEquals("buy")) {
                            int sizeSell = Integer.parseInt(words[2]);
                            if (best_ask.getSize() < sizeSell) {
                                sizeSell = sizeSell - best_ask.getSize();
                                best_ask.setSize(0);
                                updates.sort((update1, update2) -> update2.getPrice()- update1.getPrice());
                            } else {
                                best_ask.setSize(best_ask.getSize()-sizeSell);
                            }

                        }
                    }
                }
            }
            System.out.println(updates);
            scanner.close();
            outputStream.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
