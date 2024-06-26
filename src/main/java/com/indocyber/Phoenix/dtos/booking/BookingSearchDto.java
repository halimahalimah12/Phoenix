package com.indocyber.Phoenix.dtos.booking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingSearchDto {
   private final String room;
   private final String type;
   private final  String status;
   private final Integer pageNumber;
   private final Integer pageSize;

}
