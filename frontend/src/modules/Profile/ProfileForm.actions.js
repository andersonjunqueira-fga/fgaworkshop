import axios from "axios";

import { toaster } from "../../app/Notification.actions";
import { translate } from "../../components/Intl/Intl.actions";
import  { textFunctions } from '../../components/Text';

export const [ PROFILE_LOADED, PROFILE_SAVED, PROFILE_SAVE_ERROR ] = [ "PROFILE_LOADED", "PROFILE_SAVED", "PROFILE_SAVE_ERROR" ];

export const saveProfile = (profile) => {
    return dispatch => {

        let payload = Object.assign(profile, {});

        if(payload.uf === translate("selecione")) {
            payload.uf = undefined;
        }

        if(payload.ufExpedicao === translate("selecione")) {
            payload.ufExpedicao = undefined;
        }

        payload.cpf = textFunctions.clearMask(payload.cpf);
        payload.telefone = textFunctions.clearMask(payload.telefone);

        axios.post('/usuarios', payload)
            .then(function(response) {
                dispatch(toaster("perfil-salvo-sucesso", [], {status: "success"}));

            }).catch(function(error){
                dispatch(toaster(error.response.data.mensagem, [], {status: "error"}));
            });

    }
}

