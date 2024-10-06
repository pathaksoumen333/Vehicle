package com.vrp.application.service;

import com.vrp.application.model.Customer;
import com.vrp.application.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VrpService {

    public List<Assignment> solveVrp(List<Vehicle> vehicles, List<Customer> customers) {
        List<Assignment> assignments = new ArrayList<>();

        // Assign customers to vehicles
        for (Customer customer : customers) {
            boolean assigned = false;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.canServe(customer.getDemand())) {
                    vehicle.addLoad(customer.getDemand());
                    assignments.add(new Assignment(vehicle, customer));
                    assigned = true;
                    break; // Move to the next customer after assignment
                }
            }
            if (!assigned) {
                System.out.println("Customer " + customer.getName() + " cannot be served due to vehicle capacity!");
            }
        }

        return assignments; // Return the list of assignments
    }

    public static class Assignment {
        private Vehicle vehicle;
        private Customer customer;

        public Assignment(Vehicle vehicle, Customer customer) {
            this.vehicle = vehicle;
            this.customer = customer;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public Customer getCustomer() {
            return customer;
        }
    }
}
