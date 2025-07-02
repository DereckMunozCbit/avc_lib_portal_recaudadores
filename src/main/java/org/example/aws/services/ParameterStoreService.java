package org.example.aws.services;

public interface ParameterStoreService {
    String getParameter(String name, boolean withDecryption);
}