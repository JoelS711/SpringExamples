package joels.medplus.api.doctor;

import joels.medplus.api.address.DataAddress;

public record DataAnswerDoctor(Long id, String name, String email, String phone, String identification, DataAddress address) {

}
