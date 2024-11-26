module estebangmz666 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens estebangmz666.controller to javafx.fxml;

    exports estebangmz666.controller;
    exports estebangmz666.dto;
    exports estebangmz666.model;
    exports estebangmz666.service;
    exports estebangmz666.util;
    exports estebangmz666 to javafx.graphics;
}