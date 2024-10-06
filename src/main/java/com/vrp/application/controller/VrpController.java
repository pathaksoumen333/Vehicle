package com.vrp.application.controller;

import com.vrp.application.model.Customer;
import com.vrp.application.model.Vehicle;
import com.vrp.application.service.VrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VrpController {

    @Autowired
    private VrpService vrpService;

    @PostMapping("/solveVrp")
    public String solveVrp(Model model, String[] vehicleIds, int[] vehicleCapacities, String[] customerNames, int[] customerDemands) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < vehicleIds.length; i++) {
            vehicles.add(new Vehicle(vehicleIds[i], vehicleCapacities[i]));
        }

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customerNames.length; i++) {
            customers.add(new Customer(customerNames[i], customerDemands[i]));
        }

        // Solve the VRP and get assignments
        List<VrpService.Assignment> assignments = vrpService.solveVrp(vehicles, customers);

        // Prepare occupancy details for display
        StringBuilder occupancyDetails = new StringBuilder();
        occupancyDetails.append("<h2>Vehicle Occupancy Details</h2>");
        occupancyDetails.append("<table border='1'><tr><th>Vehicle ID</th><th>Occupied Percentage</th><th>Assigned Customers</th></tr>");

        for (Vehicle vehicle : vehicles) {
            occupancyDetails.append("<tr><td>").append(vehicle.getId()).append("</td><td>")
                            .append(String.format("%.2f", vehicle.getOccupancyPercentage())).append("%</td><td>");

            // List assigned customers for the vehicle
            for (VrpService.Assignment assignment : assignments) {
                if (assignment.getVehicle().getId().equals(vehicle.getId())) {
                    occupancyDetails.append(assignment.getCustomer().getName()).append(" ");
                }
            }
            occupancyDetails.append("</td></tr>");
        }
        occupancyDetails.append("</table>");

        model.addAttribute("occupancyDetails", occupancyDetails.toString());
        return "result"; // return the result view
    }
}
