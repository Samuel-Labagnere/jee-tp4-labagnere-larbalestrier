/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lp.dawin.covidpcrcenters.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class BindingErrorsResponse {

    private final List<BindingError> bindingErrors = new ArrayList<BindingError>();

    public void addError(BindingError bindingError) {
        this.bindingErrors.add(bindingError);
    }

    public void addAllErrors(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            BindingError error = new BindingError();
            error.setFieldName(fieldError.getField());
            error.setFieldValue(String.valueOf(fieldError.getRejectedValue()));
            error.setErrorMessage(fieldError.getDefaultMessage());
            addError(error);
        }
    }

    public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        String errorsAsJSON = "";
        try {
            errorsAsJSON = mapper.writeValueAsString(bindingErrors);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return errorsAsJSON;
    }

    protected static class BindingError {

        private String fieldName;
        private String fieldValue;
        private String errorMessage;

        public BindingError() {
            this.fieldName = "";
            this.fieldValue = "";
            this.errorMessage = "";
        }

        protected void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        protected void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        protected void setErrorMessage(String error_message) {
            this.errorMessage = error_message;
        }


    }

}
