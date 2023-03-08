package com.komfortcieplny.FakeEmailSender.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", initialValue = 6)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    private User(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setEmail(builder.email);
    }

    public static Builder builder() {
        return new Builder();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String email;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public User build() {
            return new User(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            if (!name.equals(builder.name)) return false;
            return email.equals(builder.email);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + email.hashCode();
            return result;
        }
    }
}
