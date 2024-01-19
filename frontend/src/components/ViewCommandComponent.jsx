import React, { Component } from 'react'
import CommandService from '../services/CommandService'

class ViewCommandComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            command: {}
        }
    }

    componentDidMount(){
        CommandService.getCommandById(this.state.id).then( res => {
            this.setState({command: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Command Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewCommandComponent
