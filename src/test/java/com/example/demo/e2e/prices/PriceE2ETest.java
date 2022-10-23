package com.example.demo.e2e.prices;

import com.example.demo.controller.dto.PriceResponseDTO;
import com.example.demo.e2e.DefaultE2ETest;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.example.demo.error.constants.ErrorMessages.PRICE_NOT_FOUND_MESSAGE;

public class PriceE2ETest extends DefaultE2ETest {

    private final String path = "/price";
    private final String format = "yyyy-MM-dd hh:mm:ss";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(format);

    @Test
    void givenDateProductAndBrand_whenGetBy_ThenReturnsPriceResponse_v1() throws ParseException {
        final PriceResponseDTO expectedResponse = PriceResponseDTO.builder()
                .productId(1)
                .brandId(1)
                .startDate(dateFormat.parse("2020-06-14 00:00:00"))
                .endDate(dateFormat.parse("2020-12-31 23:59:59"))
                .feePercentage(0.0)
                .pvp(35.5)
                .build();

        webClient.get().uri(uriBuilder ->
                        generateURI(uriBuilder, "2020-06-14 10:00:00", 1, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PriceResponseDTO.class).isEqualTo(expectedResponse);
    }

    @Test
    void givenDateProductAndBrand_whenGetBy_ThenReturnsPriceResponse_v2() throws ParseException {
        final PriceResponseDTO expectedResponse = PriceResponseDTO.builder()
                .productId(1)
                .brandId(1)
                .startDate(dateFormat.parse("2020-06-14 15:00:00"))
                .endDate(dateFormat.parse("2020-06-14 18:30:00"))
                .feePercentage(10.0)
                .pvp(27.99)
                .build();

        webClient.get().uri(uriBuilder ->
                        generateURI(uriBuilder, "2020-06-14 16:00:00", 1, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PriceResponseDTO.class).isEqualTo(expectedResponse);
    }

    @Test
    void givenDateProductAndBrand_whenGetBy_ThenReturnsPriceResponse_v3() throws ParseException {
        final PriceResponseDTO expectedResponse = PriceResponseDTO.builder()
                .productId(1)
                .brandId(1)
                .startDate(dateFormat.parse("2020-06-14 00:00:00"))
                .endDate(dateFormat.parse("2020-12-31 23:59:59"))
                .feePercentage(0.0)
                .pvp(35.5)
                .build();

        webClient.get().uri(uriBuilder ->
                        generateURI(uriBuilder, "2020-06-14 21:00:00", 1, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PriceResponseDTO.class).isEqualTo(expectedResponse);
    }

    @Test
    void givenDateProductAndBrand_whenGetBy_ThenReturnsPriceResponse_v4() throws ParseException {
        final PriceResponseDTO expectedResponse = PriceResponseDTO.builder()
                .productId(1)
                .brandId(1)
                .startDate(dateFormat.parse("2020-06-15 06:00:00"))
                .endDate(dateFormat.parse("2020-12-23 23:59:59"))
                .feePercentage(30.0)
                .pvp(50.63)
                .build();

        webClient.get().uri(uriBuilder ->
                        generateURI(uriBuilder, "2020-06-15 10:00:00", 1, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PriceResponseDTO.class).isEqualTo(expectedResponse);
    }

    @Test
    void givenDateProductAndBrand_whenGetBy_ThenReturnsPriceResponse_v5() throws ParseException {
        final PriceResponseDTO expectedResponse = PriceResponseDTO.builder()
                .productId(1)
                .brandId(1)
                .startDate(dateFormat.parse("2020-06-15 06:00:00"))
                .endDate(dateFormat.parse("2020-12-23 23:59:59"))
                .feePercentage(30.0)
                .pvp(50.63)
                .build();

        webClient.get().uri(uriBuilder ->
                        generateURI(uriBuilder, "2020-06-16 21:00:00", 1, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PriceResponseDTO.class).isEqualTo(expectedResponse);
    }

    @Test
    void givenNonExistingData_whenGetBy_ThenThrowsNotFoundException() throws ParseException {
        final int invalidId = 999;
        webClient.get().uri(uriBuilder ->
                        generateURI(uriBuilder, "2020-06-16 21:00:00", invalidId, invalidId))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .jsonPath("errorMessage")
                .isEqualTo(String.format(PRICE_NOT_FOUND_MESSAGE, invalidId, invalidId));
    }

    private URI generateURI(UriBuilder uriBuilder, String date, int productId, int brandId) {
        return uriBuilder
                .path(path)
                .queryParam("date", date)
                .queryParam("productId", productId)
                .queryParam("brandId", brandId)
                .build();
    }

}
