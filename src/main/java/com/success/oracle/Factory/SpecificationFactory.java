package com.success.oracle.Factory;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationFactory<T> {

    public Specification<T> createQuery(){

        return null;
    }
//    /**
//     * like方法
//     * @param attribute
//     * @param value
//     * @return
//     */
//    public static Specification containsLike(String attribute,String value){
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(root.get(attribute),"%"+value+"%");
//    }
//
//    /**
//     * equal匹配String类型
//     * @param attribute
//     * @param value
//     * @return
//     */
//    public static Specification<User> UserEquals(String attribute, String value){
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get(attribute),value);
//
//    }
//
//    /**
//     * equal匹配Long类型（主键id）
//     * @param attribute
//     * @param value
//     * @return
//     */
//    public static Specification UserEquals(String attribute,Long value){
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get(attribute),value);
//    }


}
