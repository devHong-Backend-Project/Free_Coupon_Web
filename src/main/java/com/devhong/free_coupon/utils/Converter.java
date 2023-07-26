package com.devhong.free_coupon.utils;

import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeConverter;
import java.util.*;

public class Converter {
    public static final String EMPTY_STRING = "";

    public static final String SEPARATOR = "#";

    @javax.persistence.Converter
    public static class RoleConverter implements AttributeConverter<Set<String>, String> {

        @Override
        public String convertToDatabaseColumn(Set<String> collection) {
            if (CollectionUtils.isEmpty(collection)) {
                return EMPTY_STRING;
            }
            return String.join(SEPARATOR, collection);
        }

        @Override
        public Set<String> convertToEntityAttribute(String dbData) {
            final Set<String> ret = new HashSet<>();

            if (Objects.isNull(dbData)) {
                return ret;
            }

            final String[] afterSplit = dbData.split(SEPARATOR);
            for (final String str : afterSplit) {
                ret.add(str.trim());
            }
            return ret;
        }
    }

    @javax.persistence.Converter
    public static class ParticipantConverter implements AttributeConverter<List<String>, String> {

        @Override
        public String convertToDatabaseColumn(List<String> collection) {
            if (CollectionUtils.isEmpty(collection)) {
                return EMPTY_STRING;
            }
            return String.join(SEPARATOR, collection);
        }

        @Override
        public List<String> convertToEntityAttribute(String dbData) {
            final List<String> ret = new ArrayList<>();

            if (Objects.isNull(dbData)) {
                return ret;
            }

            final String[] afterSplit = dbData.split(SEPARATOR);
            for (final String str : afterSplit) {
                ret.add(str.trim());
            }
            return ret;
        }
    }
}


