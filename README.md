## Diagrama de Classes Barber's Schedule | 1ª Versão

```mermaid
classDiagram
  class services {
    +id: integer
    +name: varchar
    +description: integer
    +cost: integer
  }

  class employee_services {
    +employee_id: integer
    +service_id: integer
  }

  class customer {
    +id: integer
    +name: varchar
    +address_id: integer
  }

  class customer_employee {
    +customer_id: integer
    +employee_id: integer
  }

  class employee {
    +id: integer
    +name: varchar
    +work_start_date: date
    +work_end_date: date
    +lunch_start_date: date
    +lunch_end_date: date
    +active: boolean
  }

  class customer_address {
    +id: integer
    +street: varchar
    +number: integer
    +zip_code: integer
    +city: varchar
    +state: varchar
    +country: varchar
  }

  class schedules {
    +id: integer
    +name: varchar
    +description: varchar
    +start_date: date
    +end_date: date
    +employee_id: integer
    +client_name: varchar
  }

  services "1" --* "N" employee_services
  employee_services "N" *-- "1" employee
  employee "1" --* "N" customer_employee
  employee "N" *-- "1" schedules
  customer_employee "N" *-- "1" customer
  customer "1" --* "N" customer_address
```
