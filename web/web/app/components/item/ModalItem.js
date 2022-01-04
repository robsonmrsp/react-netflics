/* Item´s Search Page generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05  */
import React from "react"

import { FormGroup, ControlLabel, FormControl, HelpBlock, Modal } from "react-bootstrap";
import JSPagination from "../core/JSTablePagination"
import DatatableConfig from "../core/DatatableConfig"
import HttpRequest from "../core/HttpRequest"
import JSInputField from "../core/JSInputField"

export default class ModalItem extends React.Component {
    static defaultProps = {
        value: {
            name: "",
            itemType: "",
            identifier: "",
            description: "",
        },
        displayValue: "name",
        idValue: "id"
    }
    constructor(props) {
        super(props)
        this.service = new HttpRequest("/rs/crud/items");
        this.state = {
            datatableConfig: new DatatableConfig(5),
            showModal: false
        }
    }
    componentDidMount = () => {
        this.paginate();
    }

    onSelect = (item) => {
        console.log(item)
        this.props.onChange(item);
        this.closeModal();
    }

    closeModal = () => {
        this.setState({ showModal: false });
    }

    openModal = () => {
        this.setState({ showModal: true });
    }

    reset = () => {
    }

    // será que é necessário usar o state??
    changePageSize = (event) => {
        const target = event.target;
        const theDatatableConfig = Object.assign(new DatatableConfig(), this.state.datatableConfig)
        theDatatableConfig.pageSize = parseInt(target.value, 10);
        theDatatableConfig.page = 1;

        this.setState({ datatableConfig: theDatatableConfig }, this.paginate);
    }


    paginate = (pageIndex = 1) => {
        const stateConf = this.state.datatableConfig;
        const datatableConfig = Object.assign(new DatatableConfig(), this.state.datatableConfig)
        datatableConfig.page = pageIndex;
        datatableConfig.loading = true;

        this.setState({ datatableConfig });

        this.service.getPage(
            datatableConfig,
            data => {
                datatableConfig.loading = false;
                datatableConfig.totalRecords = data.totalRecords;
                this.setState({ datatableConfig });
            },

            error => {
                console.error("error fetching item´s page", error);
            }
        );
    }

    changeSearchFormHandle = (event) => {
        /* Jogar essa atualização do state para fora, COMO?? */
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        const datatableConfig = { ...this.state.datatableConfig };
        datatableConfig.filterParameters[name] = value;

        this.setState({ datatableConfig });
    }

    render = () => {
        return (
            <div >
                <div className="input-group mar-btm">
                    <input type="text" className="form-control" value={this.props.value && this.props.value[this.props.displayValue]} placeholder="Nome" />
                    <span className="input-group-addon" onClick={this.openModal}>
                        <i className="fa fa-search" />
                    </span>
                </div>

                <Modal show={this.state.showModal} onHide={this.closeModal} bsSize="large" >
                    <Modal.Header closeButton>
                        <Modal.Title>Pesquisa de Item</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div className="">
                            <FormGroup controlId="controlIdName"  >
                                <ControlLabel>Nome</ControlLabel>
                                <JSInputField name="name" type="text" value={this.state.datatableConfig.filterParameters.name}  onChange={this.changeFormHandle} className="form-control" />
                            </FormGroup>
                            <FormGroup controlId="controlIdItemType"  >
                                <ControlLabel>Tipo</ControlLabel>
                                <JSInputField name="itemType" type="text" value={this.state.datatableConfig.filterParameters.itemType}  onChange={this.changeFormHandle} className="form-control" />
                            </FormGroup>
                            <FormGroup controlId="controlIdIdentifier"  >
                                <ControlLabel>Identificador</ControlLabel>
                                <JSInputField name="identifier" type="text" value={this.state.datatableConfig.filterParameters.identifier}  onChange={this.changeFormHandle} className="form-control" />
                            </FormGroup>
                            <FormGroup controlId="controlIdDescription"  >
                                <ControlLabel>Descrição</ControlLabel>
                                <JSInputField name="description" type="text" value={this.state.datatableConfig.filterParameters.description}  onChange={this.changeFormHandle} className="form-control" />
                            </FormGroup>
                            <div className="form-actions ">
                                <button type="button" onClick={(event) => this.paginate()} className=" btn btn-primary btn-large" >
                                    <i className="fa fa-search" />
                                    &nbsp;Pesquisar
						        </button>
                                <button type="button" id="btnClear" className="btnClearSupplier btn btn-info btn-large">
                                    <i className="fa fa-trash" />
                                    &nbsp;Nova Pesquisa
						        </button>
                            </div>

                            <div className="">
                                <div className="cente">
                                    <h3 className="header smaller lighter blue">Resultados</h3>
                                    <div className="table-responsive ">
                                        <table className="demo-add-niftycheck table table-hover">
                                            <thead>
                                                <tr>
		                                            <th className="th-name">
		                                                <a >
		                                                    <i className="fa " />
		                                                    nome
		                                                </a>
		                                            </th>
		                                            <th className="th-itemType">
		                                                <a >
		                                                    <i className="fa " />
		                                                    tipo
		                                                </a>
		                                            </th>
		                                            <th className="th-identifier">
		                                                <a >
		                                                    <i className="fa " />
		                                                    identificador
		                                                </a>
		                                            </th>
		                                            <th className="th-description">
		                                                <a >
		                                                    <i className="fa " />
		                                                    descrição
		                                                </a>
		                                            </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {
                                                    this.state.datatableConfig.items.map((item, index) => {
                                                        return (
                                                    <tr key={item.id}>
                                                        <td>{item.name}</td>
                                                        <td>{item.itemType}</td>
                                                        <td>{item.identifier}</td>
                                                        <td>{item.description}</td>
                                                    </tr>)
                                                    })
                                                }
                                                {this.state.datatableConfig.items.length === 0 ?
                                                    <tr className="empty"><td colSpan="2">Sem registros</td></tr> : null
                                                }
                                            </tbody>
                                        </table>
                                    </div>
                                    <div className="row page-footer-components fixed-table-pagination">
                                        <div className="col-md-6" >
                                            <div className=" float-left ">
                                                {this.state.datatableConfig.loading ?
                                                    <span className="loading-elements">
                                                        <span className="">
                                                            <i className="fa fa-spinner fa-spin fa-fw" />
                                                            Carregando...
								                        </span>
                                                    </span>
                                                    :
                                                    <span className="has-elements" >
                                                        Registros
								                        <span className="initial-page">&nbsp;{this.state.datatableConfig.first}&nbsp;</span>
                                                        a
								                        <span className="final-page">&nbsp;{this.state.datatableConfig.last}&nbsp;</span>
                                                        de
								                        <span className="total-records">&nbsp;{this.state.datatableConfig.totalRecords}</span>
                                                        . Exibindo até
								                        <select className="combo-page-size" onChange={this.changePageSize} value={this.state.datatableConfig.pageSize}>
                                                            <option value="3">3</option>
                                                            <option value="5">5</option>
                                                            <option value="10" >10</option>
                                                            <option value="25">25</option>
                                                            <option value="50">50</option>
                                                        </select>
                                                        por página.
							                        </span>
                                                }
                                            </div>
                                        </div>
                                        <div className="col-md-6" >
                                            <div className="pull-right" >
                                                <JSPagination onPaginate={this.paginate} totalItems={this.state.datatableConfig.totalRecords} page={this.state.datatableConfig.page} pageSize={this.state.datatableConfig.pageSize} />
                                            </div>
                                        </div >
                                    </div >
                                </div >
                            </div >
                        </div >
                    </Modal.Body>
                </Modal>
            </div >
        );
    }
}
