/* Classificacao´s Form generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05 */  
import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import { Alert, Button, FormGroup, ControlLabel, FormControl, HelpBlock } from "react-bootstrap";

import HttpRequest from "../core/HttpRequest";
import JSInputField from "../core/JSInputField";
import JSCombobox from "../core/JSCombobox";
import Message from "../core/Message";


import { isEmpty, isNotEmpty } from "../core/JSUtils";

const emptyClassificacao = {
	id: '',
	idadeMinima: '',    	
	nome: '',    	
	descricao: '',    	
};

export default class FormClassificacao extends React.Component {
    constructor() {
        super();
        this.service = new HttpRequest("/rs/crud/classificacaos");
        
        this.state = {
            classificacao: emptyClassificacao,
		
            message: new Message(),
            showMessage: false,

            validationFields: {
                idadeMinima: {
                    isValid: () => {
                        return isNotEmpty(this.state.classificacao.idadeMinima);
                    },
                    message: "Campo Obrigatório!",
                },
            }
        }
    }
    componentDidMount = () => {
    
    }
    getValidationState = (fieldName) => {
        if (this.state.validationFields[fieldName]) {
            if (!(this.state.validationFields[fieldName].isValid())) {
                return "error";
            }
        }
        return null;
    }
    
	getValidationMessage = (fieldName) => {
        const fieldValidator = this.state.validationFields[fieldName];
        if (fieldValidator) {
            return fieldValidator.message;
        }
        return false;
    }
    
    validateField = (fieldName) => {
        const fieldValidator = this.state.validationFields[fieldName];
        if (fieldValidator) {
            return fieldValidator.isValid && fieldValidator.isValid();
        }
        return "";
    }

    submitFormHandle = (clickEvent) => {
        console.log("Salvando o objeto: " + this.state.classificacao);
        this.service.save(
            this.state.classificacao,
            data => {
                this.classificacao = data;
				this.setState({ message: new Message("success", "Success saving Classificacao"), showMessage: true })
                this.setState({ classificacao: emptyClassificacao });

            },
            error => {
                console.error("error saving classificacao ", error);
				this.setState({ message: new Message("danger", "Error saving Classificacao"), showMessage: true })
            }
        );
    }

    changeFormDateHandle = (name, value) => {
    	const classificacao = { ...this.state.classificacao };
        classificacao[name] = value;
        this.setState({ classificacao });
    }

    render = () => {
        return (
            <div className="panel">
                <div className="panel-heading">
                    <h3 className="panel-title">Cadastro de Classificação</h3>
                </div>
                <div className="panel-body">
                    <div className="panel">
                        <div className="panel-body">
                            {this.state.showMessage ?
                                <Alert bsStyle={this.state.message.type} onDismiss={() => { this.setState({ showMessage: false }) }}>
                                    {this.state.message.text}
                                </Alert>
                                
                                : null
                            }                        
                            <form>
                                <FormGroup controlId="idadeMinima" validationState={this.getValidationState("idadeMinima")} >
                                    <ControlLabel>Idade Mínima</ControlLabel>
                                    <JSInputField name="idadeMinima" plugin="integer" type="text" value={this.state.classificacao.idadeMinima}  onChange={(event) => this.changeFormDateHandle("idadeMinima", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("idadeMinima") ? "hide" : "block"} >{this.getValidationMessage("idadeMinima")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="nome" validationState={this.getValidationState("nome")} >
                                    <ControlLabel>Nome</ControlLabel>
                                    <JSInputField name="nome" type="text" value={this.state.classificacao.nome}  onChange={(event) => this.changeFormDateHandle("nome", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("nome") ? "hide" : "block"} >{this.getValidationMessage("nome")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="descricao" validationState={this.getValidationState("descricao")} >
                                    <ControlLabel>Descrição</ControlLabel>
                                    <JSInputField name="descricao" type="text" value={this.state.classificacao.descricao}  onChange={(event) => this.changeFormDateHandle("descricao", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("descricao") ? "hide" : "block"} >{this.getValidationMessage("descricao")}</HelpBlock>
                                </FormGroup>
                                
                            </form>
                        </div>
                    </div >
                    <div >
                        <div className="clearfix form-actions">
                            <button onClick={this.submitFormHandle} className="btn btn-primary btn-lg " >
                                <i className="fa fa-check " />
                                &nbsp; Salvar
                            </button>
                            &nbsp;
                            <button onClick={this.handleClick} className="btn btn-primary btn-lg ">
                                <i className="fa fa-check " />
                                &nbsp; Salvar e continuar
                            </button>
                            &nbsp;
                            
                            <NavLink to="/classificacaos/list" className="btn-lg btn btn-light">
								<i className=" fa fa-undo " />
                                &nbsp; Voltar para a listagem
                             </NavLink>
                        </div >
                    </div >
                </div >
            </div >
        )
    }
}
