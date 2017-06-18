import React, { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, change, FieldArray } from 'redux-form';

import { Form, Row, Col, Button } from 'reactstrap';
import Card, { CardHeader, CardBody } from '../../components/Card';
import Text from '../../components/Text';
import CPF from '../../components/CPF';
import UF from '../../components/UF';
import Endereco from '../../components/Endereco';
import Phone from '../../components/Phone';
import Select from '../../components/Select';
import Email from '../../components/Email';
import Intl from '../../components/Intl';

import { toaster } from "../../app/Notification.actions";
import { translate } from "../../components/Intl/Intl.actions";
import { saveProfile } from "./ProfileForm.actions";

class ProfileForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            initialized: false,
            cursos: [
                { value: "aero", text: translate("eng-aeroespacial")},
                { value: "auto", text: translate("eng-automotiva")},
                { value: "soft", text: translate("eng-software")},
                { value: "ener", text: translate("eng-energia")},
                { value: "elet", text: translate("eng-eletronica")},
                { value: "outro", text: translate("outro")}
            ],
            showOutros: false
        }
        this.atualizaEndereco = this.atualizaEndereco.bind(this);
        this.onCursoChange = this.onCursoChange.bind(this);
    }

    componentDidUpdate() {
        if(!this.state.initialized && Object.keys(this.props.data).length > 0) {
            this.props.dispatch(this.props.initialize(this.props.data));
            this.setState(Object.assign(this.state, { initialized: true }));
        }
    }

    atualizaEndereco(address) { 
        if(address) {
            this.props.dispatch(change(this.props.form, 'logradouro', address.logradouro));
            this.props.dispatch(change(this.props.form, 'bairro', address.bairro));
            this.props.dispatch(change(this.props.form, 'cidade', address.cidade));
            this.props.dispatch(change(this.props.form, 'uf', address.uf));
        } else {
            this.props.dispatch(toaster("cep-nao-encontrado", [], {status: "warning"}));
        }
    }

    onCursoChange(event, newValue, previousValue) {
        this.setState(Object.assign(this.state, { showOutros: newValue === "outro" }));
    }

    render() {
        const { handleSubmit, pristine, reset, submitting, invalid } = this.props;
        return (
        <Form onSubmit={handleSubmit(this.props.save)}>

            <Card>
                <CardHeader><Intl str='informacoes-pessoais'></Intl></CardHeader>
                <CardBody>

                    <Row>
                        <Col xs={12} md={12}>
                            <Text name="nome" label={<Intl str='nome-completo'></Intl>} maxLength={100} required={true}/>
                        </Col>
                    </Row>

                    <Row>
                        <Col xs={12} md={3}>
                            <CPF name="cpf" label={<Intl str='cpf'></Intl>} required={true}/>
                        </Col>
                        <Col xs={12} md={3}>
                            <Text name="rg" label={<Intl str='rg'></Intl>} maxLength={20}/>
                        </Col>
                        <Col xs={6} md={3}>
                            <Text name="orgaoExpedidor" label={<Intl str='orgao-expedidor'></Intl>} maxLength={10}/>
                        </Col>
                        <Col xs={6} md={3}>
                            <UF name="ufExpedicao" label={<Intl str='uf'></Intl>}/>
                        </Col>
                    </Row>

                    <Row>
                        <Col xs={12} md={4}>
                            <Phone name="telefone" label={<Intl str='telefone'></Intl>} required={true}/>
                        </Col>
                        <Col xs={6} md={8}>
                            <Email name="email" label={<Intl str='email'></Intl>} required={true}/>
                        </Col>
                    </Row>

                    <Row>
                        <Col xs={12} md={4}>
                            <Select name="curso" label={<Intl str='curso'></Intl>} 
                                options={this.state.cursos} undefinedOption={true}
                                onChange={this.onCursoChange} required={true}/>
                        </Col>
                        {this.state.showOutros && 
                            <Col xs={12} md={8}>
                                <Text name="outroCurso" label={<Intl str='outro-curso'></Intl>} required={true}/>
                            </Col>
                        }
                    </Row>

                    <Endereco zipcodeParams={{ form: "ProfileForm", callback: this.atualizaEndereco }}/>

                </CardBody>
            </Card>

            <Button type="submit" color="primary" disabled={invalid || submitting}><Intl str='salvar'></Intl></Button>
            <Button type="button" disabled={pristine || submitting} onClick={() => this.props.dispatch(reset)}><Intl str='limpar'></Intl></Button>

        </Form>
        );
    }

}

const validate = values => {
    const errors = {};
    return errors;
}

ProfileForm = reduxForm({ 
    form: "ProfileForm", 
    validate
})(ProfileForm);

const mapStateToProps = (state) => {
    return {
        data: state.profileReducer
    }
};

ProfileForm = connect( 
    mapStateToProps,
    { save: saveProfile }
)(ProfileForm);

export default ProfileForm;
