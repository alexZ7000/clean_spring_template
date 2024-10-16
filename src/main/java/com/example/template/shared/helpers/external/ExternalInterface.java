package com.example.template.shared.helpers.external;

import java.util.Map;

final class ExternalInterface {
    protected interface IRequest {
        Map<String, Object> getData();
    }

    protected interface IResponse {
        int getStatusCode();
        Map<String, Object> getData();
    }
}
