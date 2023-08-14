package ssac.emp.pss;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String sabun;

    private String name;

    private String phone;

    private String addr;

    @Override
    public String toString() {
        return "Employee{" +
                "sabun=" + sabun +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this.sabun == ((Employee)obj).sabun;
    }
}
