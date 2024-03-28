package com.ua.project.Autobase.model;

import com.ua.project.Autobase.dao.cargo_typeDAO.CargoTypeDao;
import com.ua.project.Autobase.dao.cargo_typeDAO.CargoTypeDaoImp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    private Long id;
    private double weight;
    private long cargoTypeId;

    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
                "  Cargo Weight: " + weight + "\n" +
                "  Cargo Type ID: " + cargoTypeId + "\n" +
                "-".repeat(20);
    }
}
