package ru.t_systems.alyona.sbb.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.t_systems.alyona.sbb.dto.ChangeUserDataDTO;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {

    @InjectMocks
    PassengerServiceImpl passengerService;

    @Mock
    UserRepository userRepository;

    @Nested
    class updatePassengerData {

        private final String EXISTING_LOGIN = "mark";

        @Test
        void returnsErrorOperation_IfLoginAlreadyExists() {
            var update = new ChangeUserDataDTO();
            update.setNewLogin(EXISTING_LOGIN);
            var userEntity = mock(UserEntity.class);
            given(userRepository.getByLogin(update.getNewLogin())).willReturn(userEntity);

            OperationResultDTO result = passengerService.updatePassengerData(update);

            assertThat(result.isSuccessful()).isFalse();
        }

        @Test
        void returnsError_ifNothingToUpdate() {
            var update = new ChangeUserDataDTO();

            OperationResultDTO result = passengerService.updatePassengerData(update);

            assertThat(result.isSuccessful()).isFalse();
        }
    }
}