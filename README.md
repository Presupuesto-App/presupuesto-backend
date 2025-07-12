# 💰 Presupuesto Inteligente API

Backend desarrollado en **Spring Boot** para generar recomendaciones de presupuesto financiero mensual **personalizado con IA simulada**, basado en el modelo **50/30/20 de Elizabeth Warren**, adaptado según el perfil único de cada usuario.

✨ **¿Cansado de presupuestos genéricos?** Esta API utiliza algoritmos inteligentes que analizan tu estilo de vida, gastos y metas financieras para crear un presupuesto **100% personalizado** que realmente funcione para ti.

## 🚀 Características Destacadas

- 🔐 **Autenticación JWT** - Sistema de registro y login completamente seguro
- 🏠 **Recomendaciones personalizadas** - Ajustes basados en vivienda, familia, transporte y hábitos
- 📊 **Modelo 50/30/20 Evolutivo** - El framework clásico pero con inteligencia moderna
- 📋 **Documentación Swagger** - API interactiva para pruebas en tiempo real
- 🔒 **Spring Security 6.x** - Endpoints protegidos con las últimas prácticas de seguridad
- 🗄️ **MySQL** - Base de datos robusta para persistencia de usuarios e historial
- ⚡ **Clean Architecture** - Código mantenible siguiendo principios SOLID

## 🧠 ¿Cómo funciona la IA?

Nuestro sistema analiza **8 variables clave** de tu perfil financiero:
- 🏡 **Tipo de vivienda** (propia, alquilada, con hipoteca)
- 👥 **Situación de convivencia** (solo, en pareja, con familia)
- 👶 **Responsabilidades familiares** (hijos, dependientes)
- 🚗 **Método de transporte** (carro propio, transporte público)
- 🍽️ **Hábitos alimentarios** (frecuencia de comidas fuera)
- 💰 **Metas de ahorro** (corto, mediano y largo plazo)
- 🚨 **Situación de emergencia** (fondo de emergencia actual)
- 💳 **Estado de deudas** (activas, en proceso de pago)

Con estos datos, la IA ajusta dinámicamente los porcentajes del modelo 50/30/20, redistribuye categorías y crea un presupuesto que **evoluciona contigo**.

## 🛠️ Stack Tecnológico

- **☕ Java 17** - Lenguaje robusto y moderno
- **🍃 Spring Boot 3.2.5** - Framework líder para APIs REST
- **🛡️ Spring Security 6.x** - Seguridad de nivel enterprise
- **🔗 Spring Data JPA** - ORM simplificado y potente
- **🐬 MySQL 8.x** - Base de datos confiable y escalable
- **🔑 JWT (JJWT 0.12.x)** - Autenticación stateless moderna
- **📖 Swagger OpenAPI 3** - Documentación interactiva automática
- **🔨 Maven** - Gestión de dependencias y build automation

## 📋 Requisitos del Sistema

- Java 17+
- MySQL 8.0+
- Maven 3.6+

## ⚙️ Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/Presupuesto-App/presupuesto-backend.git 
cd presupuesto-backend
```

### 2. Configurar base de datos
Crear base de datos en MySQL:
```sql
CREATE DATABASE presupuesto_db;
```

### 3. Configurar variables de entorno
```bash
export DB_USERNAME=tu_usuario_mysql
export DB_PASSWORD=tu_password_mysql
```

O crear archivo `application-local.properties`:
```properties
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_password_mysql
```

### 4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

## 📖 Uso de la API

### Swagger UI
Accede a la documentación interactiva en: `http://localhost:8080/swagger-ui.html`

### Flujo de autenticación
1. **Registrarse**: `POST /auth/register`
2. **Iniciar sesión**: `POST /auth/login`
3. **Obtener token JWT** de la respuesta
4. **Usar botón "Authorize"** en Swagger e ingresar: `Bearer tu_token`
5. **Acceder a endpoints protegidos**: `POST /api/presupuesto/recomendar`

### Ejemplo de recomendación
```json
{
  "sueldoMensual": 3000,
  "tipoVivienda": "alquilada",
  "viveSolo": true,
  "tieneHijos": false,
  "usaCarro": true,
  "comidasFueraFrecuencia": "alta",
  "tieneMetaAhorro": true,
  "tieneDeudas": false
}
```

## 🧮 Lógica de Recomendación

### Subcategorías base (modelo 50/30/20):
- **Necesidades (50%)**: Vivienda 15%, Servicios 5%, Transporte 10%, Alimentación 20%
- **Deseos (30%)**: Moda 12%, Entretenimiento 18%
- **Ahorro (20%)**: Fondo emergencia 10%, Ahorro metas 10%

### Ajustes personalizados:
- **Vivienda propia sin hipoteca**: Vivienda 0%, redistribución a ahorro
- **Vive solo + alquilada**: Vivienda aumenta a 18%
- **Tiene hijos**: +5% Alimentación, +3% Vivienda, -3% Moda, -5% Entretenimiento
- **Usa carro**: +5% Transporte, -3% Moda, -2% Ahorro metas
- **Y más reglas inteligentes...**

## 🏗️ Arquitectura

```
src/main/java/com/financia/presupuesto/
├── auth/                # Autenticación JWT
│   ├── controller/      # Endpoints de registro/login
│   ├── service/         # Lógica de autenticación
│   ├── config/          # Configuración JWT y Security
│   └── dto/             # DTOs de entrada
├── presupuesto/         # Lógica de recomendación
│   ├── controller/      # Endpoint de recomendación
│   ├── service/         # RecomendadorService
│   ├── utils/           # Reglas de negocio
│   ├── model/           # Entidades de presupuesto
│   └── dto/             # DTOs de entrada/salida
├── usuario/             # Gestión de usuarios
│   ├── model/           # Entidad Usuario
│   └── repository/      # UsuarioRepository
└── config/              # Configuración global
```


## 📝 Contribuir

1. Fork el proyecto
2. Crear rama feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

## 👨‍💻 Autor

[Luis Chinchihualpa @u202212112](https://github.com/u202212112)
