# 💰 Presupuesto Inteligente API

Backend desarrollado en Spring Boot para generar recomendaciones de presupuesto financiero mensual personalizado basado en el modelo 50/30/20 de Elizabeth Warren, adaptado según el perfil del usuario.

## 🚀 Características

- ✅ **Autenticación JWT** - Registro y login seguro
- 🏠 **Recomendaciones personalizadas** - Basadas en tipo de vivienda, gastos y metas
- 📊 **Modelo 50/30/20 adaptativo** - Ajustes inteligentes según respuestas del usuario
- 📋 **Documentación Swagger** - API interactiva para pruebas
- 🔒 **Spring Security** - Endpoints protegidos con JWT
- 🗄️ **MySQL** - Persistencia de usuarios

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Security 6.x**
- **Spring Data JPA**
- **MySQL 8.x**
- **JWT (JJWT 0.12.x)**
- **Swagger OpenAPI 3**
- **Maven**

## 📋 Requisitos

- Java 17+
- MySQL 8.0+
- Maven 3.6+

## ⚙️ Configuración

### 1. Clonar el repositorio
```bash
git clone <tu-repositorio>
cd presupuesto-inteligente
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

## 🧪 Testing

```bash
# Ejecutar tests
mvn test

# Ejecutar con coverage
mvn test jacoco:report
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

Tu Nombre - [@tu_usuario](https://github.com/tu_usuario)
