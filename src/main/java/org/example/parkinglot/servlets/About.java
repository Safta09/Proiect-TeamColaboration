package org.example.parkinglot.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkinglot.ejb.CarsBean;

import java.io.IOException;

@WebServlet(name = "About", value = "/About")
public class About extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalParkingSpots = 10;
        int occupiedSpots = carsBean.findAllCars().size();
        int freeSpots = totalParkingSpots - occupiedSpots;

        request.setAttribute("numberOfFreeParkingSpots", Math.max(0, freeSpots));

        request.getRequestDispatcher("/WEB-INF/pages/about.jsp")
                .forward(request, response);
    }
}
