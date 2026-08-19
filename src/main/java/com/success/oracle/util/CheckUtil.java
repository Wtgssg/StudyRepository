package com.success.oracle.util;

import com.success.oracle.exception.CustomException;

import java.util.function.Supplier;

public class CheckUtil {

    /**
     * 开始校验
     * @param fieldName 字段名（用于错误提示）
     * @param value 字段值
     * @return 校验链
     */
    public static CheckChain check(String fieldName, Object value) {
        return new CheckChain(fieldName, value);
    }

    /**
     * 校验链（支持链式调用）
     */
    public static class CheckChain {
        private final String fieldName;
        private final Object value;
        private String customMsg;  // 自定义错误信息
        private boolean hasError = false;
        private String errorMsg;

        public CheckChain(String fieldName, Object value) {
            this.fieldName = fieldName;
            this.value = value;
        }



        /**
         * 校验不能为 null
         */
        public CheckChain notNull() {
            if (!hasError && value == null) {
                this.customMsg = fieldName + "不能为 null";
                this.hasError = true;
            }
            return this;
        }

        /**
         * 校验不能为 null 或空字符串（trim后）
         * value instanceof String && ((String) value).trim().isEmpty()
         */
        public CheckChain notBlank() {
            if (!hasError) {
                if (value == null) {
                    this.errorMsg = fieldName + "不能为 null";
                    this.hasError = true;
                } else if ("".equals(value)) {
                    this.errorMsg = fieldName + "不能为空";
                    this.hasError = true;
                }
            }
            return this;
        }

        /**
         * 校验不能为空（针对集合、数组）
         */
        public CheckChain notEmpty() {
            if (!hasError) {
                if (value == null) {
                    this.customMsg = fieldName + "不能为 null";
                    this.hasError = true;
                } else if (value instanceof java.util.Collection && ((java.util.Collection<?>) value).isEmpty()) {
                    this.customMsg = fieldName + "不能为空集合";
                    this.hasError = true;
                } else if (value instanceof Object[] && ((Object[]) value).length == 0) {
                    this.customMsg = fieldName + "不能为空数组";
                    this.hasError = true;
                }
            }
            return this;
        }

        /**
         * 自定义校验逻辑
         * @param predicate 校验条件，返回 true 表示校验失败
         * @param msg 失败时的错误信息
         */
        public CheckChain custom(Supplier<Boolean> predicate, String msg) {
            if (!hasError && predicate.get()) {
                this.customMsg = msg;
                this.hasError = true;
            }
            return this;
        }


        /**
         * 抛出异常，并可以指定异常类型
         */
        public <T extends RuntimeException> void throwIfInvalid(Supplier<T> exceptionSupplier) {
            if (hasError) {
                throw exceptionSupplier.get();
            }
        }

        /**
         * 抛出异常（使用默认的错误消息）
         */
        public void throwIfInvalid() throws CustomException {
            if (errorMsg != null) {
                throw new CustomException(errorMsg);  // 只传消息
            }
        }

        /**
         * 抛出异常（使用自定义的错误消息，覆盖默认的）
         * @param errorMsg 自定义错误消息
         */
        public void throwIfInvalid(String errorMsg) throws CustomException {
            if (errorMsg != null) {
                throw new CustomException(errorMsg);  // 使用自定义消息
            }
        }

        public void throwIfInvalid(String outCode,String errorMsg) throws CustomException {
            if (errorMsg != null) {
                throw new CustomException(outCode,errorMsg);  // 使用自定义消息
            }
        }


    }
}