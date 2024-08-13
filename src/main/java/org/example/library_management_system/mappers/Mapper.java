package org.example.library_management_system.mappers;

public interface Mapper <A,B> {
    B mapTo(A a);

    A mapFrom(B b);
}
