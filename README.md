# weddingplanner-App
Wedding Planner Microservices Application

This project is a microservices-based Wedding Planner application that helps to organize their weddings. The application enables users to manage wedding events, invite and track guests, schedule vendors, manage wedding budgets, and handle task lists. Each core functionality is implemented as an independent microservice, ensuring scalability and modularity.

Key Features:

Wedding Event Management

  Create and manage multiple wedding events (e.g., wedding ceremony, reception).

Guest Management

  Add guests, send invitations, and track RSVPs.
  
Vendor Scheduling

  Schedule and manage vendors (e.g., caterers, florists, photographers).
  
Budget Tracking

  Set and track wedding budgets, expenses, and cost breakdowns.

Task Management

  Create and track wedding-related tasks and to-do lists.

Notification Service

  Send reminders and alerts for important tasks and events.


Architecture

The application follows a microservices architecture where each core functionality is separated into individual microservices. These services communicate through REST APIs, and an API Gateway handles routing requests to the appropriate microservice.

Technologies Used:

Spring Boot for building microservices

Spring Cloud for configuration management and service discovery

PostgreSQL / MySQL for database integration
