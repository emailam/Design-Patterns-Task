from database_utils import DatabaseUtils


def main():
    print("=== Python SQLite3 Database Example ===")

    # Create tables
    DatabaseUtils.create_tables()

    # Add some departments
    DatabaseUtils.add_department("IT")
    DatabaseUtils.add_department("HR")
    DatabaseUtils.add_department("Finance")
    DatabaseUtils.add_department("Marketing")

    # Add some employees
    DatabaseUtils.add_employee("John", "Doe", "john.doe@company.com", "555-1234", 1)
    DatabaseUtils.add_employee("Jane", "Smith", "jane.smith@company.com", "555-5678", 1)
    DatabaseUtils.add_employee("Bob", "Johnson", "bob.johnson@company.com", "555-9012", 2)
    DatabaseUtils.add_employee("Alice", "Williams", "alice.williams@company.com", "555-3456", 3)

    # Display all departments
    DatabaseUtils.find_all_departments()

    # Display all employees
    DatabaseUtils.find_all_employees()


if __name__ == "__main__":
    main()
