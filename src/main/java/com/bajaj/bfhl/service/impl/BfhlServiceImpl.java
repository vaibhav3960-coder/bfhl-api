package com.bajaj.bfhl.service.impl;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "john_doe_17091999";
    private static final String EMAIL = "john@xyz.com";
    private static final String ROLL_NUMBER = "ABCD123";

    @Override
    public BfhlResponse processBfhlData(BfhlRequest request) {
        List<String> data = request.getData();
        if (data == null) {
            data = new ArrayList<>();
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        
        long sum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) continue;
            
            if (isInteger(item)) {
                long num = Long.parseLong(item);
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
                sum += num;
            } else if (item.matches("[a-zA-Z]+")) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = generateConcatString(alphabets);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    private boolean isInteger(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String generateConcatString(List<String> alphabets) {
        if (alphabets.isEmpty()) return "";
        
        StringBuilder combined = new StringBuilder();
        for (String s : alphabets) {
            combined.append(s);
        }
        
        StringBuilder result = new StringBuilder();
        boolean isUpper = true;
        
        // Iterate backwards over the combined string characters
        for (int i = combined.length() - 1; i >= 0; i--) {
            char ch = combined.charAt(i);
            if (isUpper) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(Character.toLowerCase(ch));
            }
            isUpper = !isUpper;
        }
        return result.toString();
    }
}
