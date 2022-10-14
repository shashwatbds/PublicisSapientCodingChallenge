import React, { Component } from "react";


export default class AddCard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            customerName: '',
            cardNumber: '',
            cardLimit: '',
            customerNameError: '',
            cardNumberError: '',
            cardLimitError: '',
            saveFailedError: '',
            disabledCNameBtn: true,
            disabledCNumBtn: true,
            disabledCLimBtn: true,
        };
    }

    submitCard(event) {
        event.preventDefault();

        let card = {
            customerName: this.customerName.value,
            cardNumber: this.cardNumber.value,
            cardLimit: this.cardLimit.value
        }

            fetch('http://localhost:8080/creditcardsystem/creditcard/save', {
            method:"POST",
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(card),
        })
        .then(response=> {this.setState({saveFailedError: response.json().response.data.message})})
        window.location.reload();
    };

    handleCustomerNameChange = event => {
        this.setState({ name: event.target.value }, () => {
          this.validateCustomerName();
        });
    };

    validateCustomerName = () => {
        let val = this.customerName.value;
        this.setState({
          customerNameError:
          val.trim().length !== 0 ? null : 'Customer Name must not be empty',
          disabledCNameBtn: val.length < 0 ? false : true
        });
    };

    handleCardLimitChange = event => {
        this.setState({ name: event.target.value }, () => {
          this.validateCardLimit();
        });
    };

    validateCardLimit = () => {
        let val = this.cardLimit.value;
        this.setState({
          cardLimitError:
          (val.length < 6 & val > 0) ? null : 'Card Limit must be between 0 and 100000 ',
          disabledCLimBtn: (val.length < 6 & this.validateCardLimit.length>0) ? false : true
        });
    };

    handleCardNumberChange = event => {
        this.setState({ name: event.target.value }, () => {
          this.validateCardNumber();
        });
    };

    validateCardNumber = () => {
        let val = this.cardNumber.value;
        this.setState({
          cardNumberError:
          (val.length < 19 & val.length > 0) ? null : 'Card Number must be between 0 and 19 digits',
          disabledCNumBtn: (val.length < 19 & val.length > 0) ? false : true
        });
    };

    render() {
        return (
            <div className="row">
                <h4 className='brand-logo'>Add</h4>
                <form className="col s12" onSubmit={this.submitCard.bind(this)}>
                <div className="row">
                    <div className="input-field col s6">
                        <input ref={node => (this.customerName = node)}  type="text" onChange={this.handleCustomerNameChange}
                        onBlur={this.validateCustomerName} className={`form-control ${this.state.customerNameError ? 'is-invalid' : ''}`}/>
                        <label htmlFor="customerName">Name</label>
                        <div className='invalid-feedback'>{this.state.customerNameError}</div>
                    </div>
                </div>
                <div className="row">
                    <div className="input-field col s6">
                        <input ref={node => (this.cardNumber = node)}  type="number" onChange={this.handleCardNumberChange}
                        onBlur={this.validateCardNumber} className={`form-control ${this.state.cardNumberError ? 'is-invalid' : ''}`}/>
                        <label htmlFor="cardNumber">Card number</label>
                        <div className='invalid-feedback'>{this.state.cardNumberError}</div>
                    </div>
                </div>
                <div className="row">
                    <div className="input-field col s6">
                        <input ref={node => (this.cardLimit = node)} type="number" onChange={this.handleCardLimitChange}
                        onBlur={this.validateCardLimit} className={`form-control ${this.state.cardLimitError ? 'is-invalid' : ''}`}/>
                        <div className='invalid-feedback'>{this.state.cardLimitError}</div>
                        <label htmlFor="cardLimit">Limit</label>
                    </div>
                </div>
                <div className="row">
                    <button className="waves-effect waves-light btn" type="submit" name="action" disabled={this.state.disabledCNumBtn & this.state.disabledCLimBtn & this.state.disabledCNameBtn}>Add</button>
                </div>
                <div className="row">
                    <div className='invalid-feedback'>{this.state.saveFailedError}</div>
                </div>
                </form>
        </div>
        )
    }
}
