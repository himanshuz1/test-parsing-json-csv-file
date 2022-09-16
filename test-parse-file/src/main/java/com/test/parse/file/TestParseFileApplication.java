package com.test.parse.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.test.parse.file.dto.Order;
import com.test.parse.file.dto.OrderDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TestParseFileApplication implements CommandLineRunner {
    //implements ApplicationRunner {

/*    @Value("${csv.file.name}")
    private String csvFilePath;

    @Value("${json.file.name}")
    private String jsonFilePath;*/


    public static void main(String[] args) {
        SpringApplication.run(TestParseFileApplication.class, args
        );
    }

    /*@Override
    public void run(ApplicationArguments args) {
        System.out.println("Name: " + csvFilePath);
        System.out.println(jsonFilePath);
        // Lambda Runnable
        Runnable taskToProcessCsvFile = () -> {
            try {
                processCsvFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        // start the thread
        new Thread(taskToProcessCsvFile).start();

        Runnable taskToProcessJsonFile = this::processJsonFile;
        // start the thread
        new Thread(taskToProcessJsonFile).start();
    }*/


    /**
     *
     */
    public void processJsonFile(String jsonFilePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Order>> typeReference = new TypeReference<List<Order>>() {
            };
            File jsonFile = new File(jsonFilePath);
            InputStream inputStream = Files.newInputStream(jsonFile.toPath());
            List<Order> orderList = mapper.readValue(inputStream, typeReference);
            String jsonFileName = getFileName(jsonFilePath);
            List<OrderDetails> listOfOrderList = orderList.stream().map(order -> new OrderDetails(order.getId(), order.getId(), order.getAmount(),
                    order.getCurrency(), order.getComment(), jsonFileName, "OK")).collect(Collectors.toList());
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            List<String> listOfJsonString = listOfOrderList.stream().map(orderDetails -> {
                try {
                    return ow.writeValueAsString(orderDetails);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            listOfJsonString.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }


    /**
     * @throws IOException
     */
    public void processCsvFile(String csvFilePath) throws IOException {
        File file = new File(csvFilePath);
        InputStream targetStream = Files.newInputStream(file.toPath());
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader(new InputStreamReader(targetStream))) {
            // create csv bean reader
            CsvToBean<Order> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Order.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            // convert `CsvToBean` object to list of users
            List<Order> orders = csvToBean.parse();

            String csvFileName = getFileName(csvFilePath);

            List<OrderDetails> listOfOrderList = orders.stream().map(order -> new OrderDetails(order.getId(), order.getId(), order.getAmount(),
                    order.getCurrency(), order.getComment(), csvFileName, "OK")).collect(Collectors.toList());
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            List<String> listOfJsonString = listOfOrderList.stream().map(orderDetails -> {
                try {
                    return ow.writeValueAsString(orderDetails);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            listOfJsonString.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName(String path) {
        if (path.contains("/")) {
            String[] array = path.split("/");
            String fileName = array[array.length - 1];
            return fileName;
        }
        return path;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running Spring Boot Application");
        String jsonFilePAth = "";
        String csvFilePath = "";
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.contains(",")) {
                    String[] arguments = arg.split(",");
                    for (String str : arguments) {
                        if (str.contains(".csv")) {
                            csvFilePath = str;
                        } else if (str.contains(".json")) {
                            jsonFilePAth = str;
                        }
                    }
                    System.out.println(csvFilePath);
                    System.out.println(jsonFilePAth);

                }
                if (StringUtils.isNotBlank(csvFilePath)) {
                    // Lambda Runnable
                    String finalCsvFilePath = csvFilePath;
                    Runnable taskToProcessCsvFile = () -> {
                        try {
                            processCsvFile(finalCsvFilePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    };
                    new Thread(taskToProcessCsvFile).start();
                }

                if (StringUtils.isNotBlank(jsonFilePAth)) {
                    String finalJsonFilePAth = jsonFilePAth;
                    Runnable taskToProcessJsonFile = () -> processJsonFile(finalJsonFilePAth);
                    // start the thread
                    new Thread(taskToProcessJsonFile).start();
                }
            }
        }
    }
}
