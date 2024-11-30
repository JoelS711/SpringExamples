package joels.medplus.api.domain.doctor;

import joels.medplus.api.domain.address.DataAddress;

public record DataAnswerDoctor(Long id, String name, String email, String phone, String identification, DataAddress address) {

}
