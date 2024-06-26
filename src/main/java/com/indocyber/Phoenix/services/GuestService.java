package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.models.Guest;
import com.indocyber.Phoenix.repositories.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public  class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest guestActive(String username) {
        return guestRepository.findUsername(username);
    }
}
