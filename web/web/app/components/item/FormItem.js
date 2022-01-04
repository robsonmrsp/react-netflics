/* Item´s Form generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05 */  
import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import { Alert, Button, FormGroup, ControlLabel, FormControl, HelpBlock } from "react-bootstrap";

import HttpRequest from "../core/HttpRequest";
import JSInputField from "../core/JSInputField";
import JSCombobox from "../core/JSCombobox";
import Message from "../core/Message";


import { isEmpty, isNotEmpty } from "../core/JSUtils";

const emptyItem = {
	id: '',
	name: '',    	
	itemType: '',    	
	identifier: '',    	
	description: '',    	
};

export default class FormItem extends React.Component {
    constructor() {
        super();
        this.service = new HttpRequest("/rs/crud/items");
        
        this.state = {
            item: emptyItem,
		
            message: new Message(),
            showMessage: false,

            validationFields: {
                name: {
                    isValid: () => {
                        return isNotEmpty(this.state.item.name);
                    },
                    message: "Campo Obrigatório!",
                },
                itemType: {
                    isValid: () => {
                        return isNotEmpty(this.state.item.itemType);
                    },
                    message: "Campo Obrigatório!",
                },
                identifier: {
                    isValid: () => {
                        return isNotEmpty(this.state.item.identifier);
                    },
                    message: "Campo Obrigatório!",
                },
                description: {
                    isValid: () => {
                        return isNotEmpty(this.state.item.description);
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
        console.log("Salvando o objeto: " + this.state.item);
        this.service.save(
            this.state.item,
            data => {
                this.item = data;
				this.setState({ message: new Message("success", "Success saving Item"), showMessage: true })
                this.setState({ item: emptyItem });

            },
            error => {
                console.error("error saving item ", error);
				this.setState({ message: new Message("danger", "Error saving Item"), showMessage: true })
            }
        );
    }

    changeFormDateHandle = (name, value) => {
    	const item = { ...this.state.item };
        item[name] = value;
        this.setState({ item });
    }

    render = () => {
        return (
            <div className="panel">
                <div className="panel-heading">
                    <h3 className="panel-title">Cadastro de Item</h3>
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
                                    <JSInputField name="name" type="text" value={this.state.item.name}  onChange={(event) => this.changeFormDateHandle("name", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("name") ? "hide" : "block"} >{this.getValidationMessage("name")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="itemType" validationState={this.getValidationState("itemType")} >
                                    <ControlLabel>Tipo</ControlLabel>
                                    <JSInputField name="itemType" type="text" value={this.state.item.itemType}  onChange={(event) => this.changeFormDateHandle("itemType", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("itemType") ? "hide" : "block"} >{this.getValidationMessage("itemType")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="identifier" validationState={this.getValidationState("identifier")} >
                                    <ControlLabel>Identificador</ControlLabel>
                                    <JSInputField name="identifier" type="text" value={this.state.item.identifier}  onChange={(event) => this.changeFormDateHandle("identifier", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("identifier") ? "hide" : "block"} >{this.getValidationMessage("identifier")}</HelpBlock>
                                </FormGroup>
                                
                                <FormGroup controlId="description" validationState={this.getValidationState("description")} >
                                    <ControlLabel>Descrição</ControlLabel>
                                    <JSInputField name="description" type="text" value={this.state.item.description}  onChange={(event) => this.changeFormDateHandle("description", event.target.value)} className="form-control" />
                                    <FormControl.Feedback />
                                    <HelpBlock className={this.validateField("description") ? "hide" : "block"} >{this.getValidationMessage("description")}</HelpBlock>
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
                            
                            <NavLink to="/items/list" className="btn-lg btn btn-light">
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

