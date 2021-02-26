package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteAndReadFileServiceCSV {
    //cac dau phan cach duoc su dung trong file .CSV
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static void writeCsvFile(ArrayList<Services> listService, String typeServices) {
        FileWriter fileWriter = null;
        try {
            String fileName = null;
            String header = "Id,Service name,Area used,Rental cost,Max people,Rental type,";
            switch (typeServices) {
                case "Villa":
                    fileName = "src/Data/Villa.csv";
                    header += "Type of room,Other amenities,Acreage pool,Num of floor";
                    break;
                case "House":
                    fileName = "src/Data/House.csv";
                    header += "Type of room,Other amenities,Num of floor";
                    break;
                case "Room":
                    fileName = "src/Data/Room.csv";
                    header += "Service free name,Unit,Price";
                    break;
            }
            assert fileName != null;
            fileWriter = new FileWriter(fileName);
            fileWriter.append(header);
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Services services : listService) {
                fileWriter.append(services.getId());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(services.getServiceName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(services.getAreaUsed()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(services.getRentalCosts()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(services.getMaxPeople()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(services.getRentalType());
                fileWriter.append(COMMA_DELIMITER);
                switch (typeServices) {
                    case "Villa":
                        fileWriter.append(((Villa) services).getTypeOfRoom());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(((Villa) services).getOtherAmenities());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(String.valueOf(((Villa) services).getAcreagePool()));
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(String.valueOf(((Villa) services).getNumOfFloor()));
                        fileWriter.append(NEW_LINE_SEPARATOR);
                        break;
                    case "House":
                        fileWriter.append(((House) services).getTypeOfRoom());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(((House) services).getOtherAmenities());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(String.valueOf(((House) services).getNumOfFloor()));
                        fileWriter.append(NEW_LINE_SEPARATOR);
                        break;
                    case "Room":
                        fileWriter.append(((Room) services).getServiceFreeObj().getServiceFreeName());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(String.valueOf(((Room) services).getServiceFreeObj().getUnit()));
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(String.valueOf(((Room) services).getServiceFreeObj().getPrice()));
                        fileWriter.append(NEW_LINE_SEPARATOR);
                        break;
                }
            }
        } catch (Exception exception) {
            System.out.println("Error in CsvFileWrite !!!");
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception exception) {
                System.out.println("Error when flush or close");
            }
        }
    }

    public static ArrayList<Services> readFileCSV(String fileName, String typeOfService) {
        BufferedReader br = null;
        ArrayList<Services> listService = new ArrayList<>();
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            try {
                FileWriter fileWriter = new FileWriter(fileName);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        try {
            String line;
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split(",");
                if (splitData[0].equals("Id")) {
                    continue;
                }
                switch (typeOfService) {
                    case "Villa":
                        Villa villa = new Villa();
                        villa.setId(splitData[0]);
                        villa.setServiceName(splitData[1]);
                        villa.setAreaUsed(Double.parseDouble(splitData[2]));
                        villa.setRentalCosts(Integer.parseInt(splitData[3]));
                        villa.setMaxPeople(Integer.parseInt(splitData[4]));
                        villa.setRentalType(splitData[5]);
                        villa.setTypeOfRoom(splitData[6]);
                        villa.setOtherAmenities(splitData[7]);
                        villa.setAcreagePool(Double.parseDouble(splitData[8]));
                        villa.setNumOfFloor(Integer.parseInt(splitData[9]));
                        listService.add(villa);
                        break;
                    case "House":
                        House house = new House();
                        house.setId(splitData[0]);
                        house.setServiceName(splitData[1]);
                        house.setAreaUsed(Double.parseDouble(splitData[2]));
                        house.setRentalCosts(Integer.parseInt(splitData[3]));
                        house.setMaxPeople(Integer.parseInt(splitData[4]));
                        house.setRentalType(splitData[5]);
                        house.setTypeOfRoom(splitData[6]);
                        house.setOtherAmenities(splitData[7]);
                        house.setNumOfFloor(Integer.parseInt(splitData[8]));
                        listService.add(house);
                        break;
                    case "Room":
                        Room room = new Room();
                        room.setId(splitData[0]);
                        room.setServiceName(splitData[1]);
                        room.setAreaUsed(Double.parseDouble(splitData[2]));
                        room.setRentalCosts(Integer.parseInt(splitData[3]));
                        room.setMaxPeople(Integer.parseInt(splitData[4]));
                        room.setRentalType(splitData[5]);
                        ServiceFree serviceFree = new ServiceFree();
                        serviceFree.setServiceFreeName(splitData[6]);
                        serviceFree.setUnit(Integer.parseInt(splitData[7]));
                        serviceFree.setPrice(Integer.parseInt(splitData[8]));
                        room.setServiceFreeObj(serviceFree);
                        listService.add(room);
                        break;
                }

            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return listService;
    }
}
