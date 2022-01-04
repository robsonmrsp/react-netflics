/* Permission´s Form generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05 */  
import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import { Alert, Button, FormGroup, ControlLabel, FormControl, HelpBlock } from "react-bootstrap";

import HttpRequest from "../core/HttpRequest";
import JSInputField from "../core/JSInputField";
import JSCombobox from "../core/JSCombobox";
import Message from "../core/Message";

import ModalItem from "../item/ModalItem";

import { isEmpty, isNotEmpty } from "../core/JSUtils";

const emptyPermission = {
	id: '',
	name: '',    	
	description: '',    	
	operation: '',    	
	tagReminder: '',    	
};

export default class FormPermission extends React.Component {
    constructor() {
        super();
        this.service = new HttpRequest("/rs/crud/permissions");
        
        this.state = {
            permission: emptyPermission,
		
            message: new Message(),
            showMessage: false,

            validationFields: {
                name: {
                    isValid: () => {
                        return isNotEmpty(this.state.permission.name);
                    },
                    message: "Campo Obrigatório!",
                },
                description: {
                    isValid: () => {
                        return isNotEmpty(this.state.permission.description);
                    },
                    message: "Campo Obrigatório!",
                },
                operation: {
                    isValid: () => {
                        return isNotEmpty(this.state.permission.operation);
                    },
                    message: "Campo Obrigatório!",
                },
                tagReminder: {
                    isValid: () => {
                        return isNotEmpty(this.state.permission.tagReminder);
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
        console.log("Salvando o objeto: " + this.state.permission);
        this.service.save(
            this.state.permission,
            data => {
                this.permission = data;
				this.setState({ message: new Message("success", "Success saving Permission"), showMessage: true })
                this.setState({ permission: emptyPermission });

            },
            error => {
                console.error("error saving permission ", error);
				this.setState({ message: new Message("danger", "Error saving Permission"), showMessage: true })
            }
        );
    }

    changeFormDateHandle = (name, value) => {
    	const permission = { ...this.state.permission };
        permission[name] = value;
        this.setState({ permission });
    }

    render = () => {
        return (
            <div className="panel">
                <div className="panel-heading">
                    <h3 className="panel-title">Cadastro de Permissão</h3>
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
                                <FormGroup controlId="name" validationState={this.getValidationState("name")} >
                                    <ControlLabel>Nome</ControlLabel>
                                    <JSInputField name="name" type="text" value={this.state.permission.name}  onChange={(event) => this.changeFormDateHandle("name", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("name") ? "hide" : "block"} >{this.getValidationMessage("name")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="description" validationState={this.getValidationState("description")} >
                                    <ControlLabel>Descrição</ControlLabel>
                                    <JSInputField name="description" type="text" value={this.state.permission.description}  onChange={(event) => this.changeFormDateHandle("description", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("description") ? "hide" : "block"} >{this.getValidationMessage("description")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="operation" validationState={this.getValidationState("operation")} >
                                    <ControlLabel>Operação</ControlLabel>
                                    <JSInputField name="operation" type="text" value={this.state.permission.operation}  onChange={(event) => this.changeFormDateHandle("operation", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("operation") ? "hide" : "block"} >{this.getValidationMessage("operation")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="tagReminder" validationState={this.getValidationState("tagReminder")} >
                                    <ControlLabel>Marcadores</ControlLabel>
                                    <JSInputField name="tagReminder" type="text" value={this.state.permission.tagReminder}  onChange={(event) => this.changeFormDateHandle("tagReminder", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("tagReminder") ? "hide" : "block"} >{this.getValidationMessage("tagReminder")}</HelpBlock>
                                </FormGroup>
                                
		                        <FormGroup controlId="titulo" validationState={this.getValidationState("item")} >
		                            <ControlLabel>Item</ControlLabel>
		                            <FormControl.Feedback />
									<ModalItem value={this.state.permission.item} displayValue="name" idValue="id" onChange={(event) => this.changeFormDateHandle("item", event.target.value)}  />
		                            <HelpBlock className={this.validateField("item") ? "hide" : "block"} >{this.getValidationMessage("item")}</HelpBlock>
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
                            
                            <NavLink to="/permissions/list" className="btn-lg btn btn-light">
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

