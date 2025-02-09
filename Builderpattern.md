```java
// Product class
class Computer {
    private String processor;
    private String ram;
    private String storage;
    private String gpu;
    private String powerSupply;

    private Computer() {}

    public static class Builder {
        private String processor;
        private String ram;
        private String storage;
        private String gpu;
        private String powerSupply;

        public Builder processor(String processor) {
            this.processor = processor;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Computer build() {
            Computer computer = new Computer();
            computer.processor = this.processor;
            computer.ram = this.ram;
            computer.storage = this.storage;
            computer.gpu = this.gpu;
            computer.powerSupply = this.powerSupply;
            return computer;
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", powerSupply='" + powerSupply + '\'' +
                '}';
    }
}

// Director class
class ComputerDirector {
    private Computer.Builder builder;

    public ComputerDirector(Computer.Builder builder) {
        this.builder = builder;
    }

    public Computer constructGamingPC() {
        return builder.processor("Intel i9 12900K")
                     .ram("32GB DDR5")
                     .storage("2TB NVMe SSD")
                     .gpu("RTX 4090")
                     .powerSupply("1000W")
                     .build();
    }

    public Computer constructOfficePC() {
        return builder.processor("Intel i5 12400")
                     .ram("16GB DDR4")
                     .storage("512GB SSD")
                     .gpu("Integrated")
                     .powerSupply("450W")
                     .build();
    }

    public Computer constructWorkstation() {
        return builder.processor("AMD Threadripper")
                     .ram("128GB DDR5")
                     .storage("4TB NVMe RAID")
                     .gpu("RTX 4000 Ada")
                     .powerSupply("1200W")
                     .build();
    }
}

// Main class to demonstrate usage
public class BuilderDemo {
    public static void main(String[] args) {
        // Using Builder directly
        Computer customComputer = new Computer.Builder()
                .processor("Intel i7")
                .ram("32GB")
                .storage("1TB SSD")
                .gpu("RTX 3080")
                .powerSupply("850W")
                .build();

        // Using Director
        ComputerDirector director = new ComputerDirector(new Computer.Builder());
        
        Computer gamingPC = director.constructGamingPC();
        Computer officePC = director.constructOfficePC();
        Computer workstation = director.constructWorkstation();

        System.out.println("Custom Computer: " + customComputer);
        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
        System.out.println("Workstation: " + workstation);
    }
}
```