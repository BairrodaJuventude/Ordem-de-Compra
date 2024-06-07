package com.bairro.ordemCompra.enterprise;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
// interface do executor de predicados do Querydsl
public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {
    // método que retorna uma lista de entidades de acordo com o predicado
    @Override
    List<T> findAll(Predicate predicate);
    // método que retorna uma lista de entidades de acordo com o filtro e a classe da entidade
    default List<T> findAll(String filter, Class<T> entityType) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder);
    }
    // método que retorna uma página de entidades de acordo com o filtro, a classe da entidade e a paginação
    default Page<T> findAll(String filter, Class<T> entityType, Pageable pageable) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder, pageable);
    }
}