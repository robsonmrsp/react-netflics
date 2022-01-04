/* Application Router  generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05 */
import React from 'react';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';

import FormLinguagem from '../components/linguagem/FormLinguagem'
import PageLinguagem from '../components/linguagem/PageLinguagem'
import FormAvaliacao from '../components/avaliacao/FormAvaliacao'
import PageAvaliacao from '../components/avaliacao/PageAvaliacao'
import FormAssinante from '../components/assinante/FormAssinante'
import PageAssinante from '../components/assinante/PageAssinante'
import FormGenero from '../components/genero/FormGenero'
import PageGenero from '../components/genero/PageGenero'
import FormCritico from '../components/critico/FormCritico'
import PageCritico from '../components/critico/PageCritico'
import FormFilme from '../components/filme/FormFilme'
import PageFilme from '../components/filme/PageFilme'
import FormVisualizacao from '../components/visualizacao/FormVisualizacao'
import PageVisualizacao from '../components/visualizacao/PageVisualizacao'
import FormClassificacao from '../components/classificacao/FormClassificacao'
import PageClassificacao from '../components/classificacao/PageClassificacao'
import FormAtor from '../components/ator/FormAtor'
import PageAtor from '../components/ator/PageAtor'
import FormUser from '../components/user/FormUser'
import PageUser from '../components/user/PageUser'
import FormRole from '../components/role/FormRole'
import PageRole from '../components/role/PageRole'
import FormPermission from '../components/permission/FormPermission'
import PagePermission from '../components/permission/PagePermission'
import FormGroup from '../components/group/FormGroup'
import PageGroup from '../components/group/PageGroup'
import FormItem from '../components/item/FormItem'
import PageItem from '../components/item/PageItem'

const Root = () => {
  return (
    <Router>
      <div>
         <Route path="/linguagems/edit/:id" component={FormLinguagem} exact />
         <Route path="/linguagems/new" component={FormLinguagem} exact />
         <Route path="/linguagems/list" component={PageLinguagem} exact />
         <Route path="/avaliacaos/edit/:id" component={FormAvaliacao} exact />
         <Route path="/avaliacaos/new" component={FormAvaliacao} exact />
         <Route path="/avaliacaos/list" component={PageAvaliacao} exact />
         <Route path="/assinantes/edit/:id" component={FormAssinante} exact />
         <Route path="/assinantes/new" component={FormAssinante} exact />
         <Route path="/assinantes/list" component={PageAssinante} exact />
         <Route path="/generos/edit/:id" component={FormGenero} exact />
         <Route path="/generos/new" component={FormGenero} exact />
         <Route path="/generos/list" component={PageGenero} exact />
         <Route path="/criticos/edit/:id" component={FormCritico} exact />
         <Route path="/criticos/new" component={FormCritico} exact />
         <Route path="/criticos/list" component={PageCritico} exact />
         <Route path="/filmes/edit/:id" component={FormFilme} exact />
         <Route path="/filmes/new" component={FormFilme} exact />
         <Route path="/filmes/list" component={PageFilme} exact />
         <Route path="/visualizacaos/edit/:id" component={FormVisualizacao} exact />
         <Route path="/visualizacaos/new" component={FormVisualizacao} exact />
         <Route path="/visualizacaos/list" component={PageVisualizacao} exact />
         <Route path="/classificacaos/edit/:id" component={FormClassificacao} exact />
         <Route path="/classificacaos/new" component={FormClassificacao} exact />
         <Route path="/classificacaos/list" component={PageClassificacao} exact />
         <Route path="/ators/edit/:id" component={FormAtor} exact />
         <Route path="/ators/new" component={FormAtor} exact />
         <Route path="/ators/list" component={PageAtor} exact />
         <Route path="/users/edit/:id" component={FormUser} exact />
         <Route path="/users/new" component={FormUser} exact />
         <Route path="/users/list" component={PageUser} exact />
         <Route path="/roles/edit/:id" component={FormRole} exact />
         <Route path="/roles/new" component={FormRole} exact />
         <Route path="/roles/list" component={PageRole} exact />
         <Route path="/permissions/edit/:id" component={FormPermission} exact />
         <Route path="/permissions/new" component={FormPermission} exact />
         <Route path="/permissions/list" component={PagePermission} exact />
         <Route path="/groups/edit/:id" component={FormGroup} exact />
         <Route path="/groups/new" component={FormGroup} exact />
         <Route path="/groups/list" component={PageGroup} exact />
         <Route path="/items/edit/:id" component={FormItem} exact />
         <Route path="/items/new" component={FormItem} exact />
         <Route path="/items/list" component={PageItem} exact />
      </div>
    </Router>
  );
};

export default Root;
