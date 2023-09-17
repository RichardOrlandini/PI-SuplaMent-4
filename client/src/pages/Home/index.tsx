
import React from 'react';
import { Header } from '../../components/Header';
import { Container } from './styles';
import {getProdutos} from './helper';

export function Home() {

  const [dataProduto, setProduto] = useState([]);
  const [paginacao, setPaginacao] = useState(0);
  const [itemBusca, setItemBusca] = useState('');


  React.useEffect(() => {
    getProdutos();
  }, []);

  return (
    <Container>

      <div>
        <Header />

        {/* tabela*/}
        <div className='estrutura-tab-PA'>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Valor</th>
                <th>Status</th>
                <th>Quantidade</th>
                <th>Visualizar</th>
                <th>Alterar</th>
              </tr>
            </thead>

            <tbody>
              {dataProduto.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.nome}</td>
                  <td>{(item.preco || 0).toLocaleString('pt-BR', {
                    style: 'currency',
                    currency: 'BRL',
                    })}
                  </td>
                  <td><button onClick={() => handleAlterarStatus(item.status, item)} className='botaoCiano'>{item.status}</button></td>
                  <td>{item.quantidade}</td>
                  <td><button className='botaoAzulPA'>Abrir</button></td>
                  <td><button className='botaoRosaPA' onClick={() => {
                    setConteudo(item)
                    setOpenModal(true)
                  }}>Alterar</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className='botoes-PA'>
            <button className="botaoAzulPA" onClick={() => handleAnterior()}>Anterior</button>
            <button className="botaoAzulPA" onClick={() => handleProximo()}>Próximo</button>
          </div>

        </div>
      </div>
    </Container>
  );
};