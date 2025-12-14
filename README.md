# Level-Up-Backend 🎮⚙️

Backend del sistema **Level-Up Gamer**, desarrollado bajo una **arquitectura de microservicios** utilizando **Spring Boot** y **Firebase Firestore** como base de datos NoSQL.  
Este repositorio centraliza los servicios responsables de la lógica de negocio de una plataforma orientada a la gestión de usuarios, productos, pedidos, carritos de compra y contacto.

---

## 📌 Descripción General

El sistema **Level-Up-Backend** provee una API REST modular y escalable que permite la comunicación entre aplicaciones cliente (Web / Mobile) y los servicios backend.  
Cada microservicio es independiente, posee su propia lógica y se comunica mediante HTTP.

---

## 🧩 Arquitectura del Sistema

- **Tipo:** Arquitectura basada en Microservicios
- **Comunicación:** REST API
- **Persistencia:** Firebase Firestore
- **Framework:** Spring Boot
- **Lenguaje:** Java
- **Gestión de dependencias:** Maven

---

## 📂 Estructura del Repositorio

```text
Level-Up-Backend/
│
├── carrito-service/        # Gestión del carrito de compras
├── contacto-service/       # Gestión de mensajes de contacto
├── pedido-service/         # Gestión de pedidos
├── producto-service/       # Gestión de productos
├── usuario-service/        # Gestión de usuarios
│
└── README.md               # Documentación principal del proyecto
