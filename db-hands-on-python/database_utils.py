import sqlite3


class DatabaseUtils:
    DB_PATH = "company.db"

    @staticmethod
    def get_connection():
        try:
            connection = sqlite3.connect(DatabaseUtils.DB_PATH)
            print("Connected to SQLite successfully!")
            return connection
        except Exception as e:
            print(f"Error connecting to database: {e}")
            return None

    @staticmethod
    def create_tables():
        connection = DatabaseUtils.get_connection()
        if not connection:
            return

        try:
            cursor = connection.cursor()

            # Create department table
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS department (
                    department_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    department_name VARCHAR(255) NOT NULL UNIQUE
                );
            """)

            # Create employee table
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS employee (
                    employee_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name VARCHAR(255) NOT NULL,
                    last_name VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    phone VARCHAR(50) NOT NULL UNIQUE,
                    department_id INTEGER,
                    FOREIGN KEY (department_id) REFERENCES department (department_id)
                );
            """)

            connection.commit()
            print("Tables created successfully!")

        except Exception as e:
            print(f"Error creating tables: {e}")
        finally:
            connection.close()

    @staticmethod
    def add_department(department_name):
        connection = DatabaseUtils.get_connection()
        if not connection:
            return

        try:
            cursor = connection.cursor()
            cursor.execute(
                "INSERT INTO department (department_name) VALUES (?)",
                (department_name,)
            )
            connection.commit()
            print(f"Department '{department_name}' added successfully!")

        except Exception as e:
            print(f"Error adding department: {e}")
        finally:
            connection.close()

    @staticmethod
    def add_employee(first_name, last_name, email, phone, department_id):
        connection = DatabaseUtils.get_connection()
        if not connection:
            return

        try:
            cursor = connection.cursor()
            cursor.execute("""
                INSERT INTO employee (first_name, last_name, email, phone, department_id) 
                VALUES (?, ?, ?, ?, ?)
            """, (first_name, last_name, email, phone, department_id))

            connection.commit()
            print(f"Employee '{first_name} {last_name}' added successfully!")

        except Exception as e:
            print(f"Error adding employee: {e}")
        finally:
            connection.close()

    @staticmethod
    def find_all_departments():
        connection = DatabaseUtils.get_connection()
        if not connection:
            return []

        try:
            cursor = connection.cursor()
            cursor.execute("SELECT department_id, department_name FROM department ORDER BY department_id")

            departments = cursor.fetchall()
            print(f"\n=== Found {len(departments)} departments ===")

            for dept in departments:
                print(f"ID: {dept[0]}, Name: {dept[1]}")

            return departments

        except Exception as e:
            print(f"Error retrieving departments: {e}")
            return []
        finally:
            connection.close()

    @staticmethod
    def find_all_employees():
        connection = DatabaseUtils.get_connection()
        if not connection:
            return []

        try:
            cursor = connection.cursor()
            cursor.execute("""
                SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone, 
                       e.department_id, d.department_name
                FROM employee e 
                JOIN department d ON e.department_id = d.department_id 
                ORDER BY e.employee_id
            """)

            employees = cursor.fetchall()
            print(f"\n=== Found {len(employees)} employees ===")

            for emp in employees:
                print(f"ID: {emp[0]}, Name: {emp[1]} {emp[2]}, Email: {emp[3]}, Dept: {emp[6]}")

            return employees

        except Exception as e:
            print(f"Error retrieving employees: {e}")
            return []
        finally:
            connection.close()
