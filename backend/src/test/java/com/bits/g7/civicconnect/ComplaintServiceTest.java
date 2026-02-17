package com.bits.g7.civicconnect;

import com.bits.g7.civicconnect.data.dao.Complaint;
import com.bits.g7.civicconnect.data.dto.ComplaintRequest;
import com.bits.g7.civicconnect.data.dto.ComplaintResponse;
import com.bits.g7.civicconnect.repository.ComplaintRepository;
import com.bits.g7.civicconnect.service.ComplaintService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTest {

    @Mock
    private ComplaintRepository repository;

    @InjectMocks
    private ComplaintService service;

    @Test
    public void testSubmitComplaint_Success() {
        // Arrange
        ComplaintRequest req = new ComplaintRequest();
        req.setFullName("John Doe");
        req.setComplaint("No water supply");

        Complaint saved = Complaint.builder().id(1L).fullName("John Doe").status(Complaint.ComplaintStatus.PENDING).build();
        when(repository.save(any(Complaint.class))).thenReturn(saved);

        // Act
        ComplaintResponse res = service.createComplaint(req);

        // Assert
        assertNotNull(res);
        assertEquals(Complaint.ComplaintStatus.PENDING, res.getStatus());
        verify(repository, times(1)).save(any(Complaint.class));
    }
}