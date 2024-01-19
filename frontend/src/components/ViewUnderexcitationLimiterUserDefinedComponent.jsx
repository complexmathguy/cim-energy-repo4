import React, { Component } from 'react'
import UnderexcitationLimiterUserDefinedService from '../services/UnderexcitationLimiterUserDefinedService'

class ViewUnderexcitationLimiterUserDefinedComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            underexcitationLimiterUserDefined: {}
        }
    }

    componentDidMount(){
        UnderexcitationLimiterUserDefinedService.getUnderexcitationLimiterUserDefinedById(this.state.id).then( res => {
            this.setState({underexcitationLimiterUserDefined: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View UnderexcitationLimiterUserDefined Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewUnderexcitationLimiterUserDefinedComponent
