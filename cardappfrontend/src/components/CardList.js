import React, { Component } from 'react';
import AddCard from './AddCard';

export default class CardList extends Component  {

	constructor(props) {
		super(props);
		this.state = {cards: [], header:['cardNumber', 'customerName', 'cardBalance', 'cardLimit']};
	}

	async componentDidMount() {
		const response = await fetch('http://localhost:8080/creditcardsystem/creditcard/fetchAll')
		const data = await response.json();
		this.setState({cards: data});
	}

	renderTableHeader() {
		let header = this.state.header;
		return header.map((key, index) => {
		   return <th key={index}>{key.toUpperCase()}</th>
		})
	 }
	 
	render() {
		return (
			<div>
				<div className='row'>
					<AddCard/>
				</div>
				<div className='row'>
					<table id='cards'>
    	    	        <tbody>
						<tr>{this.renderTableHeader()}</tr>
        	    	      {this.renderTableData()}
               			</tbody>
            		</table> 
				</div>
			</div>
		)
	}

	renderTableData() {
        return this.state.cards?.map((card, index) => {
           const { id, customerName, cardNumber, cardBalance, cardLimit } = card //destructuring
           return (
              <tr key={id}>
                 <td>{customerName}</td>
                 <td>{cardNumber}</td>
                 <td style={{color: cardBalance<0  ? 'red' : ''}}>£{cardBalance}</td>
                 <td>£{cardLimit}</td>
              </tr>
           )
        })
     }
}