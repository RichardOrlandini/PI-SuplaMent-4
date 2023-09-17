import React from 'react'
import { useState } from 'react';
import { UserContext } from '../../../UserContext';
import { GET_PRODUTOS, PUT_PRODUTOS, GET_PRODUTOS_SEM_FILTRO } from '../../../Api';
import Header from '../../header/Header';
import Modal from '../../componetesGenericos/Modal/modal';
import { FormProduc } from '../../componetesGenericos/FormAlterarProd/formProduc';
import { FormCadastarProd } from '../../componetesGenericos/FormCadastrarProd/FormCadastarProd';
import './ProdutosAdmin.css'

export const ProdutosAdmin = () => {
    const user = React.useContext(UserContext);


    const [openModal, setOpenModal] = useState(false);
    const [openModalCad, setOpenModalCad] = useState(false);
    const [conteudo, setConteudo] = useState([]);
    const [limitePagina, setLimitePaginas] = useState('')

   
    return (
        <div className='backgroud-pa'>

<Modal className='teste' isOpen={openModal} setModalOpen={() => setOpenModal(!openModal)}>
        <FormProduc
          nome={conteudo.nome}
          quantidade={conteudo.quantidade}
          status={conteudo.status}
          valor={conteudo.valor}
          avaliacao={conteudo.avaliacao}
          id={conteudo.id}>
        </FormProduc>
      </Modal>
            <Modal isOpen={openModalCad} setModalOpen={() => setOpenModalCad(!openModalCad)}>
                <FormCadastarProd></FormCadastarProd>
            </Modal>

            {!openModal && (
                <div >

                    <Header />
                    // transformar em um componente de busca.
                    <div htmlFor="">
                        {/* <img src="\src\assets\lupa.svg" alt=""/> */}
                        <input
                            onChange={(event) => setItemBusca(event.target.value)}
                            onKeyDown={handleKeyDown}
                            className="input-pesquisa"
                            placeholder='Buscar'
                            type="search"
                            name="pesquisar"
                            id="pesquisar" />
                    </div>

                    <button className='botaoRosaPA' onClick={() => { setOpenModalCad(true) }}>Adicionar</button>

                  
                </div>
            )}
        </div>

    )
}
