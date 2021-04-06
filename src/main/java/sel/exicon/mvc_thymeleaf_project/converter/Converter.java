package sel.exicon.mvc_thymeleaf_project.converter;

import java.util.Collection;

public interface Converter <T,U>{

    T toModel(U dto);
    U toDto(T model);
    Collection<T> toModels(Collection<U> dtos);
    Collection<U> toDtos(Collection<T> models);
}
