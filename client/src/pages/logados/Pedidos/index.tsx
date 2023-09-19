import { useEffect, useState } from "react";
import { api } from "../../../services/api";
import { IPaginacao } from "../../../shared/interfaces/IPaginacao";
import './pedidos.css'
import { IPedido } from "../../../shared/interfaces/IPedido";

export function Pedidos() {
    const formatador = Intl.NumberFormat('pt-br', { style: 'currency', currency: 'BRL' });
    const [pedidos, setPedidos] = useState<IPedido[]>([]);
  
    //mok
    const mokPedidos: IPedido[] = [
        {
          id: 1,
          insertionDate: new Date('2023-10-10'),
          active: true,
          client_id: 1,
          produto_id: 1,
          total: 10,
          dtEntrega: new Date('2023-10-10'),
        },
        {
          id: 2,
          insertionDate: new Date('2023-10-11'),
          active: true,
          client_id: 2,
          produto_id: 2,
          total: 20,
          dtEntrega: new Date('2023-10-11'),
        },
        {
          id: 3,
          insertionDate: new Date('2023-10-12'),
          active: true,
          client_id: 3,
          produto_id: 3,
          total: 30,
          dtEntrega: new Date('2023-10-12'),
        },
        {
          id: 4,
          insertionDate: new Date('2023-10-13'),
          active: true,
          client_id: 4,
          produto_id: 4,
          total: 40,
          dtEntrega: new Date('2023-10-13'),
        },
        {
          id: 5,
          insertionDate: new Date('2023-10-14'),
          active: true,
          client_id: 5,
          produto_id: 5,
          total: 50,
          dtEntrega: new Date('2023-10-14'),
        },
        {
          id: 6,
          insertionDate: new Date(),
          active: true,
          client_id: 6,
          produto_id: 6,
          total: 60,
          dtEntrega: new Date(),
        },
      ];

    useEffect( () => {
      //api.get<IPaginacao<IPedido>>("/pedidos")
       // .then(resp => setPedidos(resp.data.content))
       // .catch(erro => console.log(erro))
       setPedidos(mokPedidos);
    }, []);
  
    return (
      <>
        <section className="pedidos">
          <h1>Meus pedidos</h1>
          {pedidos.map(p => ( <div className="pedidos" key={p.id}>
  
            <ul>
              <li>Pedido: <strong>{p.id}</strong> </li>
              <li>Data do pedido: <strong>{new Date(p.insertionDate).toLocaleDateString()}</strong></li>
              <li>Valor total: <strong>formatador.format(p.total)</strong></li>
              <li>Entrega realizada em: <strong>{new Date(p.dtEntrega).toLocaleDateString()}</strong> </li>
            </ul>
            <button>Detalhe do pedido</button>
          </div>))}
        </section>
      </>
    )
}