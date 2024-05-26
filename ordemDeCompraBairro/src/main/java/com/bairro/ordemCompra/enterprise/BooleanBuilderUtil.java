package com.bairro.ordemCompra.enterprise;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;

import java.lang.reflect.Field;
import java.time.LocalDate;
/**
 * Classe para construção de predicados a partir de filtros

 */

public class BooleanBuilderUtil {
    /**
     * Utiliza BooleanBuilder para construir um predicado a partir de um filtro
     *
     * @param filter define o filtro
     * @param classe define a classe da entidade
     * @return um predicado de BooleanBuilder
     */
    public static BooleanBuilder buildPredicateFromFilter(String filter, Class<?> classe) {
        if (filter == null || filter.isEmpty()) {
            return new BooleanBuilder();
        }

        BooleanBuilder predicate = new BooleanBuilder();
        String[] partes = filter.split("\\+");
        // Verifica se o filtro possui 3 partes (campo, operador, valor) ex.: nome+igual+João
        if (partes.length == 3) {
            try {
                Field campo = getFieldRecursivamente(classe, partes[0]);
                campo.setAccessible(true);
                Class<?> tipoCampo = campo.getType();
                PathBuilder<?> campoPath = new PathBuilder<>(tipoCampo, campo.getName());

                // Constroi o predicado baseado no operador
                switch (partes[1].toLowerCase()) {
                    case "equal":
                        predicate.and(campoPath.eq(Expressions.constant(partes[2])));
                        break;
                    case "notEqual":
                        predicate.and(campoPath.ne(Expressions.constant(partes[2])));
                        break;
                    case "greater":
                        predicate.and(Expressions.booleanTemplate("{0} > {1}", campoPath, getTipo(tipoCampo, partes[2])));
                        break;
                    case "lesser":
                        predicate.and(Expressions.booleanTemplate("{0} < {1}", campoPath, getTipo(tipoCampo, partes[2])));
                        break;
                    case "greaterequal":
                        predicate.and(Expressions.booleanTemplate("{0} >= {1}", campoPath, getTipo(tipoCampo, partes[2])));
                        break;
                    case "lesserequal":
                        predicate.and(Expressions.booleanTemplate("{0} <= {1}", campoPath, getTipo(tipoCampo, partes[2])));
                        break;
                    case "like":
                        predicate.and(Expressions.booleanTemplate("{0} like '%'||{1}||'%'", campoPath, Expressions.constant(partes[2])));
                        break;
                    default:
                        // Operador não suportado, trate conforme necessário
                }


            } catch (NoSuchFieldException e) {
                // Campo não encontrado, trate conforme necessário
            } catch (Exception e) {
                // Acesso ilegal ao campo, trate conforme necessário
            }
        }
        //caso haja valores compostos, como datas ex.: data+entre+2021-01-01+2021-12-31
        if (partes.length == 4) {
            try {
                Field campo = getFieldRecursivamente(classe, partes[0]);
                campo.setAccessible(true);
                Class<?> tipoCampo = campo.getType();
                PathBuilder<?> campoPath = new PathBuilder<>(tipoCampo, campo.getName());


                switch (partes[1].toLowerCase()) {
                    case "between":
                        predicate.and(Expressions.booleanTemplate("{0} >= {1} AND {0} <= {2}", campoPath, getTipo(tipoCampo, partes[2]), getTipo(tipoCampo, partes[3])));
                        break;
                    default:
                        // Operador não suportado, trate conforme necessário
                }


            } catch (NoSuchFieldException e) {
                // Campo não encontrado, trate conforme necessário
            } catch (Exception e) {
                // Acesso ilegal ao campo, trate conforme necessário
            }
        }


        return predicate;
    }
  // converte um valor de string para o tipo correto
    public static Expression getTipo(Class<?> tipoCampo, String parte) {
        if (tipoCampo == Integer.class || tipoCampo == int.class) {
            return Expressions.constant(Integer.parseInt(parte));
        } else if (tipoCampo == Double.class || tipoCampo == double.class) {
            return Expressions.constant(Double.parseDouble(parte));
        } else if (tipoCampo == LocalDate.class) {
            return Expressions.constant(LocalDate.parse(parte));
        }
        return Expressions.constant(parte);
    }
    // De maneira recursiva, recupera um field de uma classe, incluindo superclasses
    private static Field getFieldRecursivamente(Class<?> classe, String nomeCampo) throws NoSuchFieldException {
        try {
            return classe.getDeclaredField(nomeCampo);
        } catch (NoSuchFieldException e) {
            if (classe.getSuperclass() != null) {
                return getFieldRecursivamente(classe.getSuperclass(), nomeCampo);
            } else {
                throw e;
            }
        }
    }
}