/* Genero´s Search Page generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05  */
import React from "react"
import { NavLink } from "react-router-dom";
import { FormGroup, ControlLabel, FormControl, HelpBlock } from "react-bootstrap";
import JSPagination from "../core/JSTablePagination"
import DatatableConfig from "../core/DatatableConfig"

import HttpRequest from "../core/HttpRequest";
import JSInputField from "../core/JSInputField";

export default class PageGenero extends React.Component {
    constructor(props) {
        super(props)
        this.service = new HttpRequest("/rs/crud/generos");
        this.state = {
            datatableConfig: new DatatableConfig(),
            showFilter: false,
        }
    }
    componentDidMount = () => {
        this.paginate();
    }
    create = () => {
        // this.$router.push({ path: "/generos/new/" });
    }

    editGenero = (_genero) => {
        // this.$router.push({ path: "/generos/edit/" + _genero.id });
        console.log("editando genero ", _genero);
    }

    reset = () => {
    }

    // será que é necessário usar o state??
    changePageSize = (event) => {
        const target = event.target;
        const theDatatableConfig = Object.assign(new DatatableConfig(), this.state.datatableConfig)
        theDatatableConfig.pageSize = parseInt(target.value, 10);
        theDatatableConfig.page = 1;

        // o estado do 
        this.setState({ datatableConfig: theDatatableConfig }, this.paginate);
    }

    removeGenero = (_genero) => {
        console.log("removendo genero ", _genero);
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
                console.error("error fetching genero´s page", error);
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
    showAdvancedSearchHandler = () => {
        console.log("Chamou o advanceSeach");
    }
    render = () => {
        return (
            <div >
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">
                            <i className="fa fa-search" />
                            &nbsp;Pesquisa de Gênero.
                        </h3>
                    </div>
                    <div className="panel-body">
                        <div id="messages_div" />
                        <form id="formGeneroFilter">
                            <div className="row">
                                <div className="page-toolbar">
                                    <div className="col-sm-6">
	                                    <NavLink className="btn btn-info" to="/generos/new">Novo Registro </NavLink>
                                        <button className="btn btn-default ">Limpar</button>
                                        <button type="button" className="btn btn-default" onClick={() => this.setState({ showFilter: !this.state.showFilter })}  > Pesquisa avançada... </button>
                                    </div>
                                    <div className="col-sm-6">
                						<div className="input-group">
                                            <input type="text" value={this.state.datatableConfig.filterParameters.nome} className="form-control" placeholder="Pesquisar Filme por por Nome" />
                                            <span id="query" className="input-group-btn ">
                                                <button type="button" onClick={(event) => this.paginate()} className="btn btn-default" >
                                                    Pesquisar
                                                </button>
                                            </span>
										</div>						
									</div>						
                                </div>
                            </div>

                            <div className="panel" style={{ display: this.state.showFilter ? "block" : "none" }} >
                                <div className="panel-heading">
                                    <h5 className="panel-title">Filtros avançados</h5>
                                </div>
                                <div className="panel-body">
	                                <FormGroup controlId="controlIdNome"  > 
	                                    <ControlLabel>Nome</ControlLabel>
	                                    <JSInputField name="nome" type="text" value={this.state.datatableConfig.filterParameters.nome}  onChange={this.changeFormHandle} className="form-control" />
	                                </FormGroup>
	                                <FormGroup controlId="controlIdDescricao"  > 
	                                    <ControlLabel>Descrição</ControlLabel>
	                                    <JSInputField name="descricao" type="text" value={this.state.datatableConfig.filterParameters.descricao}  onChange={this.changeFormHandle} className="form-control" />
	                                </FormGroup>
                                    <div>
                                        <button type="button" onClick={(event) => this.paginate()} className="btn btn-info btn-sm search-button loading-button" >
                                            <i className="fa fa-search" />
                                            &nbsp;Pesquisar
                                            </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div className="cente">
                            <div className="table-responsive ">
                                <div className="">
                                    <i className="fa fa-align-justify" />
                                    &nbsp;Resultado da pesquisa
                                </div>
                                <table className="table table-striped table-bordered table-hover dataTable no-footer">
                                    <thead>
                                        <tr>
                                            <th className="th-nome">
                                                <a >
                                                    <i className="fa " />
                                                    nome
                                                </a>
                                            </th>
                                            <th className="th-descricao">
                                                <a >
                                                    <i className="fa " />
                                                    descrição
                                                </a>
                                            </th>
                                            <th className="td-actions"> Ações </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.datatableConfig.items.map((genero, index) => {
                                                return (
                                                    <tr key={genero.id}>
                                                        <td>{genero.nome}</td>
                                                        <td>{genero.descricao}</td>
                                                        <td className="td-actions action-butons-cell">
                                                            <button className="btn btn-xs  btn-primary " onClick={(event) => this.editGenero(genero)} data-toggle="tooltip" data-placement="top" title="" data-original-title="Editar Filme">
                                                                <i className="fa fa-pencil fa-lg" />
                                                            </button>

                                                            <button className="btn btn-xs btn-danger " onClick={(event) => this.removeGenero(genero)} data-toggle="tooltip" data-placement="top" title="" data-original-title="Remover Gênero">
                                                                <i className="fa fa-trash fa-lg" />
                                                            </button>
                                                        </td>
                                                    </tr>
                                                )
                                            })
                                        }
                                    </tbody>
                                </table>
                            </div>
                            <div className="row page-footer-components">
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
            </div >
        );
    }
}

